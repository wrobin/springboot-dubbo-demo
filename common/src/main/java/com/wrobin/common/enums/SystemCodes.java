package com.wrobin.common.enums;

/**
 * 业务编号（区分业务线）
 * created by robin.wu on 2018/4/20
 **/
public enum SystemCodes {
    /** Root **/
    SYSTEM_CODES("10","system_codes","system_codes_root"),

    TEST("1001","测试系统","测试系统");

    private String code;
    private String value;
    private String descCn;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescCn() {
        return descCn;
    }

    public void setDescCn(String descCn) {
        this.descCn = descCn;
    }

    SystemCodes(String code, String value, String descCn) {
        this.code = code;
        this.value = value;
        this.descCn = descCn;
    }


}
