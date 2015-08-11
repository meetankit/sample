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

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.citrix.g2w.microservice.apiproxy.dto.AuthenticationToken;

/**
 * Created by Gaurav on 03/08/15.
 */
@Component
@Setter
@Getter
@ConfigurationProperties(prefix="authService")
public class AuthClientServiceImpl implements AuthClientService {

    @Autowired
    private RestTemplate restTemplate;

    private String baseUrl; 

    @Override
    public AuthenticationToken verifyToken(String token) {
        return restTemplate.getForObject(baseUrl + "/tokens/{token}", AuthenticationToken.class, token);
    }
}
