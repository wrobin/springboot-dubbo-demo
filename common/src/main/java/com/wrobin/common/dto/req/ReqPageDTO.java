package com.wrobin.common.dto.req;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * created by robin.wu on 2018/4/23
 **/
@Data
@NoArgsConstructor
public class ReqPageDTO<T> implements Serializable{
    /** 请求对象**/
    private T data;
    /** 当前页码**/
    private Integer currentPage;
    /**每页显示条数**/
    private Integer pageSize;
}
