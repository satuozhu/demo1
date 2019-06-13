package com.users.component.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对手机号与邮箱的简单校验
 *
 * @author lyl
 * @date 2019/3/18 17:22
 */
public class CheckUtil {

    /**
     * 邮箱校验
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        try {
            // 正常邮箱
            // /^\w+((-\w)|(\.\w))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/

            // 含有特殊 字符的 个人邮箱 和 正常邮箱
            // js: 个人邮箱
            // /^[\-!#\$%&'\*\+\\\.\/0-9=\?A-Z\^_`a-z{|}~]+@[\-!#\$%&'\*\+\\\.\/0-9=\?A-Z\^_`a-z{|}~]+(\.[\-!#\$%&'\*\+\\\.\/0-9=\?A-Z\^_`a-z{|}~]+)+$/

            // java：个人邮箱
            // [\\w.\\\\+\\-\\*\\/\\=\\`\\~\\!\\#\\$\\%\\^\\&\\*\\{\\}\\|\\'\\_\\?]+@[\\w.\\\\+\\-\\*\\/\\=\\`\\~\\!\\#\\$\\%\\^\\&\\*\\{\\}\\|\\'\\_\\?]+\\.[\\w.\\\\+\\-\\*\\/\\=\\`\\~\\!\\#\\$\\%\\^\\&\\*\\{\\}\\|\\'\\_\\?]+

            // 范围 更广的 邮箱验证 “/^[^@]+@.+\\..+$/”
            final String pattern1 = "[\\w.\\\\+\\-\\*\\/\\=\\`\\~\\!\\#\\$\\%\\^\\&\\*\\{\\}\\|\\'\\_\\?]+@[\\w.\\\\+\\-\\*\\/\\=\\`\\~\\!\\#\\$\\%\\^\\&\\*\\{\\}\\|\\'\\_\\?]+\\.[\\w.\\\\+\\-\\*\\/\\=\\`\\~\\!\\#\\$\\%\\^\\&\\*\\{\\}\\|\\'\\_\\?]+";

            final Pattern pattern = Pattern.compile(pattern1);
            final Matcher mat = pattern.matcher(email);
            return mat.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * CJYFIXME搜集号段时间:2017-11-28(这个之后的请自行添加) 手机号:目前全国有27种手机号段。
     * 移动有19个号段：134（0-8）、135、136、137、138、139、147(147（数据卡）)、148(物联网)、150、151、152、
     * 157、158、159、178、182、183、184、187、188、198。
     * 联通有11种号段：130、131、132、--145(数据卡)--、146(物联网)、155、156、166、171、175、176、185、
     * 186。 电信有7个号段：133、--1349--、149、153、173、177、180、181、189、199。 虚拟运营商:
     * (1).移动:1703、1705、1706 (2).联通:1704、1707、1708、1709、171
     * (3).电信:1700、1701、1702 卫星通信:1349
     * <p>
     * 工业和信息化部公示了2017年第10批“电信网码号资源使用证书”颁发结果，批准同意部分单位提出的电信网码号资源有关申请，
     * 其中三大运营商均获得相关物联网号段。 移动: (1).198(0-9)号段(公众移动通信网号) (2).148(0-9)号段(物联网业务专用号段)
     * (3).1440(0-9)号段(物联网网号) (4).(460)13(移动网络识别码) 联通: (1).166(0-9)号段(公众移动通信网号)
     * (2).146(0-9)号段(物联网业务专用号段) 电信: (1).1740(0-5)号段(卫星移动通信业务号)、
     * (2).199(0-9)号段(公众移动通信网号)、 (3).1410(0-9)号段(物联网网号)、 (4).(460)59(移动网络识别码)
     * 由于物联网号段一般用在家用家具上，所以这里不考虑物联网号段,物联网号码的总位数是13或者14还没搞清楚
     * =========================================================================
     * ======================
     * 总结一下:虚拟运营商、数据卡、物联网、卫星通信、移动网络识别码都不作为正常使用的电话号码,所以需要验证的手机号如下:
     * 130、131、132、133、134(0-8)、135、136、137、138、139 149
     * 150、151、152、153、155、156、157、158、159 166、 173、175、176、177、178、
     * 180、181、182、183、184、185、186、187、188、189 198、199
     */
    private static final String REGEX_MOBILE = "(134[0-8]\\d{7})" + "|(" + "((13([0-3]|[5-9]))" + "|149"
            + "|15([0-3]|[5-9])" + "|166" + "|17(3|[5-8])" + "|18[0-9]" + "|19[8-9]" + ")" + "\\d{8}" + ")";

    /**
     * 判断是否是手机号
     *
     * @param tel 手机号
     * @return boolean true:是 false:否
     */
    public static boolean isMobile(String tel) {
        return Pattern.matches(REGEX_MOBILE, tel);
    }

    /**
     * 可以是：字母、数字、下划线，一种或多种搭配，且在6-20位
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigitOne(String str) {
        return str.matches("^[a-zA-Z0-9_]{6,20}$");
    }

    /**
     * 可以是：字母、数字、下划线，两种或多种搭配，且在6-20位
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigitTwo(String str) {
        //^ 匹配一行的开头位置
        //(?![0-9]+$) 预测该位置后面不全是数字
        //(?![a-zA-Z]+$) 预测该位置后面不全是字母
        //(?![_]+$) 预测该位置后面不全是下划线
        //[0-9A-Za-z] {6,20} 由8-16位数字或这字母组成
        //$ 匹配行结尾位置
        //String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{2,20}$";
        //String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)(?![_]+$)[0-9A-Za-z_]{6,20}$";
        return str.matches("^(?![0-9]+$)(?![a-zA-Z]+$)(?![_]+$)[0-9A-Za-z_]{6,20}$");
    }

    /**
     * 同时包含字母和数字，可以有下划线，且在6-20位
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigitThree(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;//包含数字
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;//包含字母
            }
        }
        return isDigit && isLetter && str.matches("^[a-zA-Z0-9_]{6,20}$");
    }

//    public static void main(String[] args) {
//        System.out.println("isMobile(\"13049881621\") = " + isMobile("13049881621"));
//        System.out.println("isEmail(\"1751670861@qq.com\")= " + isEmail("1751670861@qq.com"));
//        System.out.println("isLetterDigitOne(\"123456\")="+isLetterDigitOne("123456"));
//        System.out.println("isLetterDigitTwo(\"123456\")="+isLetterDigitTwo("12345_"));
//        System.out.println("isLetterDigitThree(\"123456\")="+isLetterDigitThree("12345a"));
//    }

}
