package com.wrobin.interceptors;

import com.wrobin.common.annotations.HttpAuthentication;
import com.wrobin.common.enums.RespStatus;
import com.wrobin.common.exceptions.AuthorizationException;
import com.wrobin.proxy.AuthProxy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * token拦截器
 * 查看access_token是否有效
 * created by robin.wu on 2018/3/19
 **/
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private AuthProxy authProxy;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ((handler instanceof HandlerMethod)) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            HttpAuthentication httpAuthentication = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), HttpAuthentication.class);
            //判断请求的接口是否是否需要验证
            if ((httpAuthentication != null && httpAuthentication.ignore())) {
                log.debug("AuthorizationInterceptor =>preHandle=> @HttpAuthentication annotation and bypass the request.");
                return super.preHandle(request, response, handlerMethod);
            }

            String authorization = request.getHeader(AUTHORIZATION);
            if(StringUtils.isNotBlank(authorization) && authorization.startsWith("Bearer")) {
                String encodedCredential = authorization.substring("Bearer".length()).trim();

                String credential = new String(Base64.decodeBase64(encodedCredential), Charset.forName("UTF-8"));
                log.debug("AuthorizationInterceptor =>preHandle=>trying to do signature verification for token[{}]",credential);
                boolean validation = authProxy.verifyToken(credential);

                if(!validation) {
                    throw new AuthorizationException(RespStatus.TOKEN_EXPIRED.getCode(), "Access token validation does not pass");
                }


                return super.preHandle(request, response, handler);
            }
            throw new AuthorizationException(RespStatus.LOGOUT.getCode(), "Http basic authorization required");
        }

        return super.preHandle(request, response, handler);
    }
}
