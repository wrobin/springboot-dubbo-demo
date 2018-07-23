package com.wrobin.common.exceptions;

import com.wrobin.common.enums.RespStatus;

/**
 * 通用的异常类
 * created by robin.wu on 2018/3/15
 **/
public class CommonException extends RuntimeException implements IException {
    private int code;
    private String title;
    private String errorMsg;

    public CommonException(int code){
        this(code,"COMMON_ERROR_000001","System Error");
    }

    public CommonException(String errorMsg) {
        super(errorMsg);
        this.code = RespStatus.FAIL.getCode();
        this.errorMsg = errorMsg;
    }

    public CommonException(int code, String errorMsg) {
        super(errorMsg);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public CommonException(int code,String errorMsg,String title) {
        super(errorMsg);
        this.code = code;
        this.title = title;
        this.errorMsg = errorMsg;
    }


    @Override
    public int getErrorCode() {
        return code;
    }

    @Override
    public String getErrorTitle() {
        return title;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
