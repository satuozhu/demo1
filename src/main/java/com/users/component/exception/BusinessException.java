package com.users.component.exception;

/**
 * BusinessException:
 * 自定义异常
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -5149308597897785323L;

    private String msg;
    private int code = 500;

    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BusinessException(int code, String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

//    public BusinessException(String message) {
//        super(message);
//    }
//
//    @Override
//    public Throwable fillInStackTrace() {
//        return this;
//    }

}
