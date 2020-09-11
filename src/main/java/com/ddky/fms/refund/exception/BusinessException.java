package com.ddky.fms.refund.exception;

/**
 * @author a
 */
public class BusinessException extends RuntimeException {

    private int code;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
    }
}
