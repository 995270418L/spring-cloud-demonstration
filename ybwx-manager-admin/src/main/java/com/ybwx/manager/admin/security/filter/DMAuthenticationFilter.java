package com.ybwx.manager.admin.security.filter;

import com.ybwx.common.util.json.JSONSnakeUtils;
import com.ybwx.manager.admin.vo.LoginRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class DMAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public DMAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                ||request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){
            log.info("dm authentication start");
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream is = request.getInputStream()){
                LoginRequestVO loginRequestVO = JSONSnakeUtils.readValue(is, LoginRequestVO.class);
                authRequest = new UsernamePasswordAuthenticationToken(
                        loginRequestVO.getUsername(), loginRequestVO.getPassword());
            }catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            }finally {
                authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }
        return null;
    }
}
