package com.wrobin.common.enums;

/**
 * created by robin.wu on 2018/3/15
 **/
public enum YesOrNo {
    YES("y","1"),
    NO("n","0");

    private String key;
    private String value;

    YesOrNo(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
