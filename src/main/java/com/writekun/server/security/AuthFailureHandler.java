package com.writekun.server.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.writekun.server.utils.JSONReturnBody;
import com.writekun.server.utils.ReturnStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        JSONReturnBody<Object> ret = null;
        if (e instanceof AccountExpiredException) {
            // 账号过期
            ret = new JSONReturnBody<>(ReturnStatus.USER_ACCOUNT_EXPIRED);
        } else if (e instanceof BadCredentialsException) {
            // 密码错误
            ret = new JSONReturnBody<>(ReturnStatus.USER_PASSWORD_ERROR);
        } else if (e instanceof CredentialsExpiredException) {
            // 密码过期
            ret = new JSONReturnBody<>(ReturnStatus.USER_PASSWORD_EXPIRED);
        } else if (e instanceof DisabledException) {
            // 账号不可用
            ret = new JSONReturnBody<>(ReturnStatus.USER_ACCOUNT_DISABLE);
        } else if (e instanceof LockedException) {
            // 账号锁定
            ret = new JSONReturnBody<>(ReturnStatus.USER_ACCOUNT_LOCKED);
        } else if (e instanceof InternalAuthenticationServiceException) {
            // 用户不存在
            ret = new JSONReturnBody<>(ReturnStatus.USER_ACCOUNT_NOT_EXIST);
        } else {
            // 未知错误
            ret = new JSONReturnBody<>(ReturnStatus.UNKNOWN_ERROR);
        }

        httpServletResponse.setContentType("text/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(httpServletResponse.getWriter(), ret);
    }
}
