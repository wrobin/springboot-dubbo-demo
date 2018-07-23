package com.wrobin.auth.po;

import com.wrobin.parent.po.BasePO;
import lombok.Data;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.UUID;
import javax.persistence.*;

@Data
@Table(name = "by_token")
public class Token extends BasePO{
    /**
     * 用户表业务主键(一个用户不同的应用有不同的token)
     */
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 过期时间
     */
    @Column(name = "expire_time")
    private Date expireTime;

    /**
     * 刷新时间
     */
    @Column(name = "refresh_time")
    private Date refreshTime;

    /**
     * 系统编号
     */
    @Column(name = "system_code")
    private String systemCode;

    /**
     * 客户端来源
     */
    private String client;
    public Token(){}

    public Token(Long userId,String systemCode) {
        this.userId = userId;
        this.systemCode = systemCode;
        this.accessToken = UUID.randomUUID().toString();
        this.refreshToken = UUID.randomUUID().toString();
        this.createTime = new Date();
        this.expireTime = DateUtils.addMinutes(this.createTime, 1440);  // set expire time in 1440 minutes
    }

    public boolean isExpire() {
        return this.expireTime.before(new Date());
    }

    public Token refresh(int expireDuration) {
        this.accessToken = UUID.randomUUID().toString();
        this.refreshToken = UUID.randomUUID().toString();
        this.refreshTime = new Date();
        this.expireTime = DateUtils.addMinutes(this.refreshTime, expireDuration > 0 ? expireDuration : 180);  // set expire time in expireDuration
        return this;
    }

}