package com.users.component.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 微信开放平台通用接口工具类
 *
 * @author lyl
 * @date 2019/4/8 16:21
 */
@Slf4j
@Component
public class WeixinUtil {

    /**
     * 【1微信网页授权】生成扫描二维码，获取code
     */
    public static final String WECHART_QRCONNECT = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

    /**
     * 【2微信网页授权】通过code获取access_token
     */
    public static final String WECHART_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     * 【3微信网页授权】因接口频率有次数限制，如果需要，刷新access_token
     */
    public static final String WECHART_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

    /**
     * 【4微信网页授权】通过access_token和openid获取用户的基础信息，包括头像、昵称、性别、地区
     */
    public static final String WECHART_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";

    /**
     * 【5微信网页授权】检验授权凭证（access_token）是否有效
     */
    public static final String WECHART_AUTH = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";

    /**
     * 用户的appID
     */
    @Value("${wechat.appId}")
    private String appId;
    /**
     * 用户的appsecret
     */
    @Value("${wechat.secret}")
    private String secret;

    /**
     * redis工具类
     */
    @Autowired
    private RedisUtils redisUtils;

    //网站应用微信登录开发指南
    //https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=&lang=zh_CN
    //
    //access_token是调用授权关系接口的调用凭证，由于access_token有效期（目前为2个小时）较短，当access_token超时后，可以使用refresh_token进行刷新，
    //access_token刷新结果有两种：
    //1. 若access_token已超时，那么进行refresh_token会获取一个新的access_token，新的超时时间；
    //2. 若access_token未超时，那么进行refresh_token不会改变access_token，但超时时间会刷新，相当于续期access_token。
    //refresh_token拥有较长的有效期（30天），当refresh_token失效的后，需要用户重新授权。
    //
    //redisUtils.set("wechat_access_token"+openid, json.getString("access_token"), 2 * 60 * 60L);// 两小时后失效
    //redisUtils.set("wechat_refresh_token"+openid, json.getString("refresh_token"), 30 * 24 * 60 * 60L);// 30天后失效
    //当使用access_token调用接口，若access_token已失效，可根据refresh_token，获取access_token（方法3，openid和refresh_token存redis缓存中）
    //若refresh_token也失效，则需重新授权登录（方法2）

    /**
     * 【方法1微信网页授权】生成扫描二维码，获取code
     *
     * @return
     */
    public String qrconnect(String redirectUri, String scope, String state) {
        log.info("【方法1微信网页授权】生成扫描二维码，获取code");

        //https://open.weixin.qq.com/connect/qrconnect?appid=AppID&redirect_uri=urlencode(https://www.baidu.com)&response_type=code&scope=snsapi_login&state=2014#wechat_redirect
        //点击微信图标后，把链接返回过去
        //用户使用微信APP扫描
        //1号店示例
        //https://passport.yhd.com/passport/login_input.do
        //https://passport.yhd.com/passport/login_input.do?returnUrl=http%3A%2F%2Fwww.yhd.com
        //https://passport.yhd.com/baidu/login.do
        //https://open.weixin.qq.com/connect/qrconnect?appid=wxbdc5610cc59c1631&redirect_uri=https%3A%2F%2Fpassport.yhd.com%2Fwechat%2Fcallback.do&response_type=code&scope=snsapi_login&state=613cdcb56fba6f32f43d0a502c11170a#wechat_redirect
        //String redirectURL = "https://open.weixin.qq.com/connect/qrconnect?appid=wxbdc5610cc59c1631&redirect_uri=https%3A%2F%2Fpassport.yhd.com%2Fwechat%2Fcallback.do&response_type=code&scope=snsapi_login&state=613cdcb56fba6f32f43d0a502c11170a#wechat_redirect";

        //scope：应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即
        String url = WECHART_QRCONNECT.replace("APPID", appId).replace("REDIRECT_URI", redirectUri).replace("SCOPE", scope).replace("STATE", state);
        log.info("url=" + url);
        return url;
    }

    /**
     * 【方法2微信网页授权】通过code获取access_token
     *
     * @param code
     * @return
     */
    public JSONObject accessToken(String code) {
        log.info("【方法2微信网页授权】通过code获取access_token");
        String url = WECHART_ACCESS_TOKEN.replace("APPID", appId).replace("SECRET", secret).replace("CODE", code);
        log.info("url=" + url);
        JSONObject json = doGetstr(url);
        log.info("json=" + json);
        if (json != null) {
            log.info("access_token=" + json.getString("access_token"));
            log.info("expires_in=" + json.getInteger("expires_in"));//7200s=2h
            log.info("refresh_token=" + json.getString("refresh_token"));
            log.info("scope=" + json.getString("scope"));
            log.info("openid=" + json.getString("openid"));
            log.info("unionid=" + json.getString("unionid"));
//            redisUtils.set("wechat_access_token"+json.getString("openid"), json.getString("access_token"), 2 * 60 * 60L);// 两小时后失效
//            redisUtils.set("wechat_refresh_token"+json.getString("openid"), json.getString("refresh_token"), 30 * 24 * 60 * 60L);// 30天后失效
        }
        return json;
    }

    /**
     * 【方法3微信网页授权】因接口频率有次数限制，如果需要，刷新access_token
     *
     * @return
     */
//    public JSONObject refreshToken(String openid, String refresh_token) {
    public JSONObject refreshToken(String refresh_token) {
        log.info("【方法3微信网页授权】因接口频率有次数限制，如果需要，刷新access_token");
        JSONObject json = null;

//        Boolean isRefreshToken = redisUtils.exists("wechat_refresh_token"+openid);//refresh_token过期，则重新授权
//        Boolean isAccessToken = redisUtils.exists("wechat_access_token"+openid);//access_token过期，则刷新
//        if (!isRefreshToken){
//            //重新授权（方法2）
//        }
//        if (isRefreshToken && !isAccessToken) {
//            String refresh_token = redisUtils.get("wechat_refresh_token"+openid).toString();
//            //String access_token = redisUtils.get("wechat_access_token"+openid).toString();
            String url = WECHART_REFRESH_TOKEN.replace("APPID", appId).replace("REFRESH_TOKEN", refresh_token);
            log.info("url=" + url);
            json = doGetstr(url);
            log.info("json=" + json);
            if (json != null) {
                log.info("access_token=" + json.getString("access_token"));
                log.info("expires_in=" + json.getInteger("expires_in"));//7200s=2h
                log.info("refresh_token=" + json.getString("refresh_token"));
                log.info("openid=" + json.getString("openid"));
                log.info("scope=" + json.getString("scope"));
//                redisUtils.set("wechat_access_token"+openid, json.getString("access_token"), 2 * 60 * 60L);// 两小时后失效
            }
//        }
        return json;
    }

    /**
     * 【方法4微信网页授权】通过access_token和openid获取用户的基础信息，包括头像、昵称、性别、地区
     *
     * @param accessToken
     * @param openid
     * @return
     */
    public JSONObject userinfo(String accessToken, String openid) {
        log.info("【方法4微信网页授权】通过access_token和openid获取用户的基础信息，包括头像、昵称、性别、地区");
        String url = WECHART_USERINFO.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);
        log.info("url=" + url);
        //JSONObject json3 = doGetstr(url3);
        JSONObject json = httpRequest(url, "GET", null);
        log.info("json=" + json);
        if (json != null) {
            log.info("unionid=" + json.getString("unionid"));
            log.info("openid=" + json.getString("openid"));
            log.info("nickname=" + json.getString("nickname"));
            log.info("sex=" + json.getInteger("sex"));
            log.info("headimgurl=" + json.getString("headimgurl"));
            log.info("country=" + json.getString("country"));
            log.info("province=" + json.getString("province"));
            log.info("city=" + json.getString("city"));
            log.info("language=" + json.getString("language"));
            log.info("privilege=" + json.getJSONArray("privilege"));
        }
        return json;
    }

    /**
     * 【方法5微信网页授权】检验授权凭证（access_token）是否有效
     *
     * @param accessToken
     * @param openid
     * @return
     */
    public JSONObject auth(String accessToken, String openid) {
        log.info("【方法5微信网页授权】检验授权凭证（access_token）是否有效");
        String url = WECHART_AUTH.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);
        log.info("url=" + url);
        JSONObject json = doGetstr(url);
        log.info("json=" + json);
        return json;
    }

    /**
     * 处理doget请求
     *
     * @param url
     * @return
     */
    public JSONObject doGetstr(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
//                jsonObject = JSONObject.fromObject(result);
                jsonObject = JSONObject.parseObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 处理post请求
     *
     * @param url
     * @return
     */
    public JSONObject doPoststr(String url, String outStr) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            httpPost.setEntity(new StringEntity(outStr, "utf-8"));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            //jsonObject = JSONObject.fromObject(result);
            jsonObject = JSONObject.parseObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
//            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
//            TrustManager[] tm = { new MyX509TrustManager() };
//            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//            sslContext.init(null, tm, new java.security.SecureRandom());
//            // 从上述SSLContext对象中得到SSLSocketFactory对象
//            SSLSocketFactory ssf = sslContext.getSocketFactory();
//
//            URL url = new URL(requestUrl);
//            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
//            httpUrlConn.setSSLSocketFactory(ssf);

            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);// 发送POST请求必须设置如下两行
            httpUrlConn.setDoInput(true);// 发送POST请求必须设置如下两行
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            //jsonObject = JSONObject.fromObject(buffer.toString());
            jsonObject = JSONObject.parseObject(buffer.toString());
            log.info("---->httpRequest传回的信息是：" + jsonObject);
        } catch (ConnectException ce) {
            log.info("Weixin server connection timed out.");
        } catch (Exception e) {
            log.info("https request error:{}" + e.getMessage());
        }
        return jsonObject;
    }

}