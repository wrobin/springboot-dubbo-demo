package com.wrobin.common.dto.resp;

import com.wrobin.common.enums.RespStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Optional;

/**
 * created by robin.wu on 2018/3/15
 **/
@Getter
@Setter
@NoArgsConstructor
public class RespDTO<T> implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;

    private int code = RespStatus.SUCCESS.getCode();
    private T data;
    private String error;   //错误信息
    private String message;  //自定义的信息

    public static <T> RespDTO<T> ok(){
        return new RespDTO<>();
    }

    public static <T> RespDTO<T> ok(T obj){
        RespDTO<T> respDTO = new RespDTO<>();
        respDTO.setCode(RespStatus.SUCCESS.getCode());
        respDTO.setData(obj);
        return respDTO;
    }

    public static <T> RespDTO<T> fail(String error) {
        return fail(RespStatus.FAIL.getCode(),error);
    }

    public static <T> RespDTO<T> fail(int code, String error) {
        return fail(code,error,"");
    }

    public static <T> RespDTO fail(int code, String error,String message) {
        RespDTO<T> respDTO = new RespDTO<>();
        respDTO.setCode(code != RespStatus.SUCCESS.getCode() ? code : RespStatus.FAIL.getCode());
        respDTO.setError(error);
        respDTO.setMessage(message);
        return respDTO;
    }

    /**
     * 转换或复制错误结果
     */
    public static <T> RespDTO<T> fail(RespDTO<?> failure){
        RespDTO<T> respDTO = new RespDTO<>();
        respDTO.setCode(failure.getCode() != RespStatus.SUCCESS.getCode() ? failure.getCode() : RespStatus.FAIL.getCode());
        respDTO.setError(failure.getError());
        respDTO.setMessage(failure.getMessage());
        return respDTO;
    }

    /**
     * 成功或失败的包装
     * @param obj
     * @param errMsg obj为null时返回的错误消息
     * @param <T>
     * @return
     */
    public static <T> RespDTO<T> result(T obj,String errMsg) {
        Optional<T> optional = Optional.ofNullable(obj);
        return optional.map(t->RespDTO.ok(t)).orElse(RespDTO.fail(RespStatus.FAIL.getCode(),errMsg));
    }


    /** 判断返回结果是否成功 */
    public boolean isSuccess() {
        return code == RespStatus.SUCCESS.getCode();
    }
    /** 判断返回结果是否有结果对象 */
    public boolean hasObject() {
        return code == RespStatus.SUCCESS.getCode() && data !=null;
    }

}
