package com.wrobin.parent.service.impl;

import com.wrobin.common.distributeID.IdGenerator;
import com.wrobin.parent.po.BasePO;
import com.wrobin.parent.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Optional;

/**
 * created by robin.wu on 2018/5/9
 **/
@Slf4j
public class AbstractBaseService<T extends BasePO> implements BaseService<T> {
    @Autowired
    private Mapper<T> mapper;

    @Override
    public Optional<T> save(T t) {
        t.setId(IdGenerator.instance().nextId());
        Date now  = new Date();
        try {
            Method mCreateTime = t.getClass().getDeclaredMethod("setCreateTime", Date.class);
            if(mCreateTime != null) {
                mCreateTime.invoke(t, now);
            }
        }catch (NoSuchMethodException e) {
            log.info(t.getClass().getName() + " has no createTime field");
        } catch (IllegalAccessException e) {
            log.error("AbstractBaseService=>save createTime",e);
        } catch (InvocationTargetException e) {
            log.error("AbstractBaseService=>save createTime",e);
        }

        try {
            Method mLastModifyTime = t.getClass().getDeclaredMethod("setLastModifyTime",Date.class);
            if(mLastModifyTime != null) {
                mLastModifyTime.invoke(t, now);
            }
        } catch (NoSuchMethodException e) {
            log.info(t.getClass().getName() + " has no lastModifyTime field");
        } catch (IllegalAccessException e) {
            log.error("AbstractBaseService=>save lastModifyTime",e);
        } catch (InvocationTargetException e) {
            log.error("AbstractBaseService=>save lastModifyTime",e);
        }

        int insertRows = mapper.insertSelective(t);
        return insertRows <=0 ? Optional.empty() : Optional.ofNullable(t);
    }

    @Override
    public Optional<T> updateById(T t) {
        if(t.getId() == null) {
            return Optional.empty();
        }

        try {
            Method mLastModifyTime = t.getClass().getDeclaredMethod("setLastModifyTime",Date.class);
            if(mLastModifyTime != null) {
                mLastModifyTime.invoke(t, new Date());
            }
        } catch (NoSuchMethodException e) {
            log.error("AbstractBaseService=>updateById",e);
        } catch (IllegalAccessException e) {
            log.error("AbstractBaseService=>updateById",e);
        } catch (InvocationTargetException e) {
            log.error("AbstractBaseService=>updateById",e);
        }

        int updateRows = mapper.updateByPrimaryKeySelective(t);
        return updateRows <= 0 ? Optional.empty() : Optional.ofNullable(t);
    }

    @Override
    public Optional<T> getById(Long id) {
        return Optional.ofNullable(mapper.selectByPrimaryKey(id));
    }

    @Override
    public Optional<Integer> deleteById(Long id) {
        return Optional.ofNullable(mapper.deleteByPrimaryKey(id));
    }

    public Mapper<T> getMapper(){
        return mapper;
    }
}
