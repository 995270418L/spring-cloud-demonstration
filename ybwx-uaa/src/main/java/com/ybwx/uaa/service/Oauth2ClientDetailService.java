package com.ybwx.uaa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

@Slf4j
public class Oauth2ClientDetailService implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.info("演示： load client info from database ... ");
        return new BaseClientDetails("client_1","DEMO_RESOURCE_ID","all","authorization_code","client", "https://www.baidu.com");
    }
}
