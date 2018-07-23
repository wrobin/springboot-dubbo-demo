package com.wrobin.auth.service.impl;

import com.wrobin.auth.dao.mapper.TokenMapper;
import com.wrobin.auth.po.Token;
import com.wrobin.auth.service.TokenService;
import com.wrobin.parent.service.impl.AbstractBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Optional;

/**
 * created by robin.wu on 2018/3/15
 **/
@Service
@Transactional
public class TokenServiceImpl extends AbstractBaseService<Token> implements TokenService {
    private static final int EXPIRE_DURATION = 1440;
    @Autowired
    private TokenMapper tokenMapper;

    /**
     * 根据accessToken获取
     * @param accessToken
     * @return
     */
    @Override
    public Optional<Token> getTokenByAccessToken(String accessToken) {
        Token tokenParam = new Token();
        tokenParam.setAccessToken(accessToken);
        return getTokenSelective(tokenParam);
    }

    @Override
    public Optional<Token> getTokenByUserId(Long userId) {
        Token tokenParam = new Token();
        tokenParam.setUserId(userId);
        return getTokenSelective(tokenParam);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    @Override
    public Optional<Token> refreshToken(Token token) {
        Token refreshedToken = token.refresh(EXPIRE_DURATION);
        return this.updateById(refreshedToken);
    }

    /**
     * 条件获取token
     * @param tokenParam
     * @return
     */
    @Override
    public Optional<Token> getTokenSelective(Token tokenParam) {
        if(tokenParam.getId() != null) {
            return this.getById(tokenParam.getId());
        }

        Example example = new Example(Token.class,false);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(tokenParam.getAccessToken())) {
            criteria.andEqualTo("accessToken",tokenParam.getAccessToken());
        }else if(tokenParam.getUserId() !=null) {
            criteria.andEqualTo("userId",tokenParam.getUserId());;
        }

        return Optional.ofNullable(tokenMapper.selectOneByExample(example));
    }
}
