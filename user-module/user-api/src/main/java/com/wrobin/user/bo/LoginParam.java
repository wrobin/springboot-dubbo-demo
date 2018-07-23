package com.wrobin.user.bo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * created by robin.wu on 2018/7/23
 **/
@Data
public class LoginParam implements Serializable {
    private String username;
    private String password;
    private String systemCode;

    public LoginParam(String username,String password,String systemCode){
        this.username = username;
        this.password = password;
        this.systemCode = systemCode;
    }

    public boolean isValid() {
        return StringUtils.isNotBlank(username)
                && StringUtils.isNotBlank(password)
                && StringUtils.isNotBlank(systemCode);
    }
}
