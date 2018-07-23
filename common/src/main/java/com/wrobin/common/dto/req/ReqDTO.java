package com.wrobin.common.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * created by robin.wu on 2018/3/15
 **/
@Getter
@Setter
@NoArgsConstructor
public class ReqDTO<T> implements Serializable{
    private String source;
    private String sign;

    private T data;
}
