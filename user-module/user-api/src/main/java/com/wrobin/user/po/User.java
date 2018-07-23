package com.wrobin.user.po;

import com.wrobin.parent.po.BasePO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Table(name = "by_user")
public class User extends BasePO{
    /**
     * 手机号
     */
    @Column(name = "mobile_no")
    private String mobileNo;
    /**
     * 密码
     */
    private String password;
    /**
     * 系统编号
     */
    @Column(name = "system_code")
    private String systemCode;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private User(Builder builder) {
        setId(builder.id);
        setMobileNo(builder.mobileNo);
        setPassword(builder.password);
        setSystemCode(builder.systemCode);
        setCreateTime(builder.createTime);
    }

    public static final class Builder {
        private Long id;
        private String mobileNo;
        private String password;
        private String systemCode;
        private Date createTime;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder mobileNo(String val) {
            mobileNo = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder systemCode(String val) {
            systemCode = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}