package com.wrobin.common.dto.resp;

import com.wrobin.common.enums.RespStatus;
import com.wrobin.common.util.PageInfo;
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
public class RespPageDTO<T> implements Serializable{
    private int code;
    private PageInfo<T> data;
    private String error;   //错误信息
    private String message;  //自定义的错误信息

    public static <T> RespPageDTO<T> ok(){
        return new RespPageDTO<>();
    }

    public static <T> RespPageDTO<T> ok(PageInfo<T> data){
        RespPageDTO<T> respDTO = new RespPageDTO<>();
        respDTO.setCode(RespStatus.SUCCESS.getCode());
        respDTO.setData(data);
        return respDTO;
    }

    public static <T> RespPageDTO<T> fail(String error) {
        return fail(RespStatus.FAIL.getCode(),error);
    }

    public static <T> RespPageDTO<T> fail(int code, String error) {
       return fail(code,error,"");
    }

    public static <T> RespPageDTO fail(int code,String error, String message) {
        RespPageDTO<T> respDTO = new RespPageDTO<>();
        respDTO.setCode(code);
        respDTO.setError(error);
        respDTO.setMessage(message);
        return respDTO;
    }

    /**
     * 成功或失败的包装
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RespPageDTO<T> result(PageInfo<T> data, String errMsg) {
        Optional<PageInfo<T>> optional = Optional.ofNullable(data);
        return optional.map(t->RespPageDTO.ok(t)).orElse(RespPageDTO.fail(RespStatus.FAIL.getCode(),errMsg));
    }

    /** 判断返回结果是否成功 */
    public boolean isSuccess() {
        return code == RespStatus.SUCCESS.getCode();
    }
}
