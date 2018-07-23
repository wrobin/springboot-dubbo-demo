package com.wrobin.common.exceptions;

/**
 * Created by wangnan on 2016/6/21.
 */
public interface IException{
    int getErrorCode();
    String getErrorTitle();
    String getErrorMsg();
}
