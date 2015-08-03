package com.citrix.g2w.microservice.Service;

import com.citrix.g2w.microservice.api.dto.AuthenticationToken;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Gaurav on 03/08/15.
 */
public class TokenValidatorServiceImpl implements TokenValidatorService {

    private RestTemplate restTemplate;

    private String url = "http://authed1svc.qai.expertcity.com/authentication-service/tokens/{token}";

    @Required
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public AuthenticationToken getResponse(String token) {
        return restTemplate.getForObject(url, AuthenticationToken.class, token);
    }
}
