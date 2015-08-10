/*
 * Copyright (c) 1998-2015 Citrix Online LLC
 * All Rights Reserved Worldwide.
 *
 * THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO CITRIX ONLINE
 * AND CONSTITUTES A VALUABLE TRADE SECRET.  Any unauthorized use,
 * reproduction, modification, or disclosure of this program is
 * strictly prohibited.  Any use of this program by an authorized
 * licensee is strictly subject to the terms and conditions,
 * including confidentiality obligations, set forth in the applicable
 * License and Co-Branding Agreement between Citrix Online LLC and
 * the licensee.
 */
package com.citrix.g2w.microservice.apiproxy.service;

import com.citrix.g2w.microservice.apiproxy.dto.AuthenticationToken;

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
