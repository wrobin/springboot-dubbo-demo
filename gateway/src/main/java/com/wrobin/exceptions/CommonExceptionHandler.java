package com.wrobin.exceptions;

import com.wrobin.common.dto.resp.RespDTO;
import com.wrobin.common.enums.RespStatus;
import com.wrobin.common.exceptions.AuthorizationException;
import com.wrobin.common.exceptions.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * created by robin.wu on 2018/3/15
 * 通用的异常处理
 **/
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    /**
     * 处理Exception类异常
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public <T> RespDTO<T> handler(Exception e){
        log.error("CommonExceptionHandler=>handler=>",e);
        return RespDTO.fail(RespStatus.FAIL.getCode(),"系统异常");
    }

    /**
     * 处理自定义的CommonException类异常
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public <T> RespDTO<T> commonHandler(CommonException e) {
        log.error("CommonExceptionHandler=>commonHandler=>",e);
        return RespDTO.fail(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.OK)
    public <T> RespDTO<T> handleAuthorizationException(AuthorizationException exception) {
        log.error("CommonExceptionHandler=>handleAuthorizationException=>",exception);
        return RespDTO.fail(exception.getCode(),exception.getMessage());
    }
}
