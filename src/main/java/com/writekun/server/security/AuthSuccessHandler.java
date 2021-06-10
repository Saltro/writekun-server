package com.writekun.server.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.writekun.server.utils.JSONReturnBody;
import com.writekun.server.utils.ReturnStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("text/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        JSONReturnBody<Object> ret = new JSONReturnBody<>(ReturnStatus.USER_LOGIN_SUCCESS);
        objectMapper.writeValue(httpServletResponse.getWriter(), ret);
    }
}
