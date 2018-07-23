package com.wrobin.parent.service;

import com.wrobin.parent.po.BasePO;

import java.util.Optional;

/**
 * created by robin.wu on 2018/5/9
 **/
public interface BaseService<T extends BasePO> {
    Optional<T> save(T t);

    Optional<T> updateById(T t);

    Optional<T> getById(Long id);

    Optional<Integer> deleteById(Long id);
}
