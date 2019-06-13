package com.users.component.entity;

/**
 * 状态枚举类
 */
public class STATUS {

    public static final int CODE_SUCCESS = 200;
    public static final String MSG_SUCCESS = "操作成功";
    public static final String MSG_SEND_SUCCESS = "发送成功";
    public static final String MSG_YES_ID_CARD = "身份证合法";
    public static final String MSG_NO_ID_CARD = "身份证不合法";

    public static final int CODE_ERR = 407;
    public static final String MSG_ERR = "操作失败";
    public static final String MSG_SEND_ERR = "发送失败";

    public static final int CODE_FAILURE = 500;
    public static final String MSG_FAILURE = "程序异常";

    public static final int CODE_NULL = 400;
    public static final String MSG_NULL = "参数为空";

    public static final int CODE_PARAMETER_ERROR = 401;
    public static final String MSG_PARAMETER_ERROR = "参数错误";
    public static final String MSG_PASSWORD_ERROR = "密码错误";
    public static final String MSG_PASSWORD_REPWD_NO = "两次密码不一致";
    public static final String MSG_CODE_ERROR = "验证码错误";
    public static final String MSG_YES_USER_ACCOUNT = "账号已存在";
    public static final String MSG_BIND_USER_ACCOUNT = "账号已被绑定";
    public static final String MSG_NO_USER_ACCOUNT = "账号不存在";
    public static final String MSG_USER_ACCOUNT_ERROR = "账号格式错误";
    public static final String MSG_USER_ACCOUNT_REGEX_ERROR = "账号格式错误，6-20位之间的字母、数字、下划线组合";
    public static final String MSG_TOKEN_ERROR = "token非法";

    public static final int ERROR = -1;//未知原因的失败

}
