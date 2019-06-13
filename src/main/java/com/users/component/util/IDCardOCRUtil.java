package com.users.component.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 身份证图像识别
 *
 * @author lyl
 * @date 2019/3/16 17:03
 */
public class IDCardOCRUtil {

    public static void main(String[] args) {
        //1识别网络图片
//        String imageUrl = "https://crimgs.oss-cn-shenzhen.aliyuncs.com/icon/2019-03-18/4cc3b774c26d4b93af828b85d46f98f2-zhangsan.jpg?Expires=1868241232&OSSAccessKeyId=LTAIxKsDiZWFoUzP&Signature=PkixLR4NMoJkjBEvmZUaFTyKxaA%3D";
//        https://crimgs.oss-cn-shenzhen.aliyuncs.com/icon/2019-03-18/4cc3b774c26d4b93af828b85d46f98f2-zhangsan.jpg
//        https://crimgs.oss-cn-shenzhen.aliyuncs.com/icon/2019-03-18/4cc3b774c26d4b93af828b85d46f98f2-zhangsan.jpg?Expires=1868241232&OSSAccessKeyId=LTAIxKsDiZWFoUzP&Signature=PkixLR4NMoJkjBEvmZUaFTyKxaA%3D
        //2识别本地图片(base64)
        String imageUrl = "D:/myTest/奥巴马身份证.jpg";
        String idCardSide = "front";//正面front，背面back
        JSONObject jsonObject = IDCardOCR(imageUrl, idCardSide, 2);//1识别网络图片 2识别本地图片
        if (null != jsonObject) {
//            JSONObject jsonObject = JSONObject.parseObject(result);
            System.out.println("code = " + jsonObject.get("code"));
            System.out.println("msg = " + jsonObject.get("msg"));
            JSONObject obj = jsonObject.getJSONObject("result");
            //正面
            System.out.println("address = " + obj.get("address"));// 获取地址
            System.out.println("birthday = " + obj.get("birthday"));// 获取生日
            System.out.println("name = " + obj.get("name"));// 获取名字
            System.out.println("code = " + obj.get("code"));// 获取身份证号
            System.out.println("sex = " + obj.get("sex"));// 获取性别
            System.out.println("nation = " + obj.get("nation"));// 获取民族
            //反面
            System.out.println("issue = " + obj.get("issue"));// 签发机关
            System.out.println("issueDate = " + obj.get("issueDate"));// 签发日期
            System.out.println("expiryDate = " + obj.get("expiryDate"));// 失效日期
            //System.out.printf(" name : %s \n sex : %s", name, sex);
        }
    }

//    /** 正面返回 **/
//    {
//        "code": "1",
//            "msg": "查询成功",
//            "result": {
//        "address": "贵州省松桃苗族自治县真旗屯村湾头组",
//                "birthday": "19850911",
//                "name": "张三",
//                "code": "522229198509112018",
//                "sex": "男",
//                "nation": "汉"
//    }
//    }
//    /** 反面返回 **/
//    {
//        "code": "1",
//            "msg": "查询成功",
//            "result": {
//        "issue": "绵阳市公安局浩城分局",  /* 签发机关 */
//                "issueDate": "20150729",          /* 签发日期 */
//                "expiryDate": "20250729"          /* 失效日期 */
//    }
//    }

//    AppKey：25821301
//    AppSecret：38a5732987dbceec855b78f898b5f03d
//    AppCode：0bc0083a3535454fbbb92cb721a919b9
//    调用地址：http(s)://ocridcard.market.alicloudapi.com/idimages
//    请求方式：POST
//    返回类型：JSON

    /**
     * 【图像识别OCR】身份证识别 - 身份证OCR
     * 【AI技术】支持识别身份证正反面信息，包括姓名，出生年月，生日，身份证号信息。
     * 扫描身份证ocr,识别准确率高达98%以上，对URL网络图片或base64信息进行识别。支持PNG、JPG、JPEG、BMP。
     * 简单版完整优化代码下载：http://code.fegine.com/sdk/cmapi028676.zip
     *
     * @param imageUrl   图片路径
     * @param idCardSide 正面front，背面back
     * @param type       1识别网络图片 2识别本地图片
     * @return
     */
    public static JSONObject IDCardOCR(String imageUrl, String idCardSide, int type) {

        String result = null;
        String host = "https://ocridcard.market.alicloudapi.com";
        String path = "/idimages";
        String method = "POST";
        String appcode = "0bc0083a3535454fbbb92cb721a919b9";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("idCardSide", idCardSide);//默认正面front，背面请传back
        if (type == 1) {
            //识别网络图片
            //bodys.put("image", "http://img3.fegine.com/image/idcard.jpg");
            bodys.put("image", imageUrl);
        } else if (type == 2) {
            //识别本地图片(base64)
            //bodys.put("image", "data:image/jpeg;base64,........");   //jpg图片
            //bodys.put("image", "data:image/png;base64,........");   //png图片
            String binaryToString = getImageBinaryToString(imageUrl);
            //String binaryToString = getImageBinary(imageUrl);
            bodys.put("image", "data:image/jpeg;base64," + binaryToString);//jpg图片
        } else {
            return null;
        }
        System.out.println(bodys);

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            //System.out.println("===" + response.toString());//如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            //获取response的body
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===" + result);
        return JSONObject.parseObject(result);
    }

    /**
     * 获取图片的base64编码数据
     * </p>
     *
     * @param imagePath
     * @return
     */
    public static String getImageBinaryToString(String imagePath) {
        try {
            File file = new File(imagePath);
            byte[] content = new byte[(int) file.length()];
            FileInputStream finputstream = new FileInputStream(file);
            finputstream.read(content);
            finputstream.close();
            return new String(Base64.encodeBase64(content));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取jpg图片的base64编码数据
     *
     * @param imagePath
     * @return
     */
    public static String getImageBinary(String imagePath) {
        BASE64Encoder encoder = new BASE64Encoder();
        File f = new File(imagePath);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
