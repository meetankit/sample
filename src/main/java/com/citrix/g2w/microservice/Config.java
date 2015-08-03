package com.citrix.g2w.microservice;

import com.citrix.accountrest.service.CollaborationAccountServiceImpl;
import com.citrix.authentication.service.AuthenticationServiceRest;
import com.citrix.authentication.service.AuthenticationServiceRestImpl;
import com.citrix.collaboration.services.account.CollaborationAccountService;
import com.citrix.security.authentication.RestAuthenticationTokenService;
import com.citrix.security.authentication.RestAuthenticationTokenServiceImpl;
import com.citrix.util.mapping.Assembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Gaurav on 31/07/15.
 */
@Configuration
public class Config {

    @Autowired
    private Assembler assembler;

    @Bean
    public RestAuthenticationTokenService restAuthenticationTokenService() {
        RestAuthenticationTokenServiceImpl restAuthenticationTokenService = new RestAuthenticationTokenServiceImpl();
        restAuthenticationTokenService.setAuthenticationServiceRest(authenticationServiceRest());
        restAuthenticationTokenService.setCollaborationAccountService(collaborationAccountService());
        return restAuthenticationTokenService;
    }

    @Bean
    AuthenticationServiceRest authenticationServiceRest() {
        AuthenticationServiceRestImpl authenticationServiceRest = new AuthenticationServiceRestImpl();
        authenticationServiceRest.setAuthenticationServiceBaseUrl("http://authed1svc.qai.expertcity.com/authentication-service");
        authenticationServiceRest.setAuthSystemToken("internalservices");
        authenticationServiceRest.setCacheExpirationTimeMillis(300000);
        authenticationServiceRest.setRestTemplate(restTemplate());
        return authenticationServiceRest;
    }

    @Bean
    RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(5000);
        simpleClientHttpRequestFactory.setReadTimeout(5000);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(simpleClientHttpRequestFactory);
        return restTemplate;
    }

    @Bean
    CollaborationAccountService collaborationAccountService() {
        CollaborationAccountServiceImpl collaborationAccountService = new CollaborationAccountServiceImpl();
        collaborationAccountService.setAccountAssembler(assembler);
        collaborationAccountService.setSettingsAssembler(assembler);
        collaborationAccountService.setUserAssembler(assembler);
        return collaborationAccountService;
    }
}
