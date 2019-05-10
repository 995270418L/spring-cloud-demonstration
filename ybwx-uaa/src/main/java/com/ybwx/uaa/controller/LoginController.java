package com.ybwx.uaa.controller;

import com.google.common.collect.Maps;
import com.ybwx.common.enums.ResultStatusEnum;
import com.ybwx.common.util.WebUtil;
import com.ybwx.common.util.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.util.Assert;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/oauth")
public class LoginController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private TokenStore tokenStore;

    @PostMapping(value = "/login")
    public ResultVO<?> login(@RequestBody LoginVO loginVO, HttpServletResponse response) throws HttpRequestMethodNotSupportedException {
        Map<String, String> params = Maps.newHashMap();
        String clientId = "datamesh_app";
        params.put("client_id", clientId);
        params.put("grant_type", "password");
        params.put("username", loginVO.getUsername());
        params.put("password", loginVO.getPassword());
        params.put("scope", "all");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(clientId, "", Lists.newArrayList());
        ResponseEntity<OAuth2AccessToken> oauth2Token = tokenEndpoint.postAccessToken(usernamePasswordAuthenticationToken,params);
        OAuth2AccessToken oAuth2AccessToken = oauth2Token.getBody();
        // TODO JWT Token generator
        // .....
        return new ResultVO<>(oAuth2AccessToken);
    }

    @PostMapping(value = "/refresh")
    public ResultVO<?> refresh(HttpServletRequest request) throws HttpRequestMethodNotSupportedException {
        String refreshToken = request.getParameter("refresh_token");
        Assert.notNull(refreshToken, "refresh_token can't be null");
        Map<String, String> params = Maps.newHashMap();
        String clientId = "datamesh_app";
        params.put("client_id", clientId);
        params.put("grant_type", "refresh_token");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(clientId, "", Lists.newArrayList());
        ResponseEntity<OAuth2AccessToken> oauth2Token = tokenEndpoint.postAccessToken(usernamePasswordAuthenticationToken,params);
        OAuth2AccessToken oAuth2AccessToken = oauth2Token.getBody();
        // TODO JWT Token generator
        // .....
        return new ResultVO<>(oAuth2AccessToken.getAdditionalInformation());
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public ResultVO<?> logout(HttpServletRequest request){
        String token = WebUtil.extractToken(request);
        log.info("extract token: {}", token);
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResultVO<>(ResultStatusEnum.OK);
    }

    // TODO customer GET /oauth/me
}
