package com.ybwx.uaa.exception;

import com.ybwx.common.enums.ResultStatusEnum;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        response.setStatus(ResultStatusEnum.NOT_LOGGED_IN.getCode());
        response.getOutputStream().println(accessDeniedException.getMessage());
    }
}
