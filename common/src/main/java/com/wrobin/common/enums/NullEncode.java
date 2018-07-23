package com.wrobin.common.enums;

/**
 * created by robin.wu on 2018/5/18
 **/
public enum NullEncode {
    NULL(-1L,"NULL");

    private Long code;
    private String value;

    NullEncode(Long key, String value) {
        this.code = key;
        this.value = value;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
