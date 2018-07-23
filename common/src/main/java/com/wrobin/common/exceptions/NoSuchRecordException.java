package com.wrobin.common.exceptions;

import com.wrobin.common.enums.RespStatus;

/**
 * 未找到相关记录的异常类
 * Created by Administrator on 2018/5/7 0007.
 */
public class NoSuchRecordException extends RuntimeException implements IException {
    private int code;
    private String title;
    private String errorMsg;

    public NoSuchRecordException(String errorMsg) {
        super(errorMsg);
        this.code = RespStatus.FAIL.getCode();
        this.errorMsg = errorMsg;
    }

    public NoSuchRecordException(int code, String errorMsg) {
        super(errorMsg);
        this.code = code;
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
