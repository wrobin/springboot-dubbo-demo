package com.wrobin.common.enums;

/**
 * created by robin.wu on 2018/3/15
 **/
public enum RespStatus {
    SUCCESS(0),
    FAIL(-1),

    LOGOUT(1001),
    TOKEN_EXPIRED(1002),
    AUTHORIZED(1003);

    RespStatus(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
}
