package com.ddky.fms.refund.model;

import lombok.Data;

/**
 * 返回对象
 * @author a
 */
@Data
public class ResponseObject {

    private int code;
    private String message;
    private Object data;

    public ResponseObject(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseObject() {
    }

    @Override
    public String toString() {
        return "ResponseObject [code=" + code + ", message=" + message + ", data=" + data + "]";
    }


}