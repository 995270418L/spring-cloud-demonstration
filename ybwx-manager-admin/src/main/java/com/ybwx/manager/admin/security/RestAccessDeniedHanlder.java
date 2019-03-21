package com.ybwx.manager.admin.security;

import com.ybwx.common.enums.ResultStatusEnum;
import com.ybwx.common.util.json.JSONSnakeUtils;
import com.ybwx.common.util.vo.ResultVO;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 非匿名登陆才走这个handler
 */
@Component
public class RestAccessDeniedHanlder implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(200);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JSONSnakeUtils.writeValue(new ResultVO(ResultStatusEnum.NOT_LOGGED_IN)));
    }
}
