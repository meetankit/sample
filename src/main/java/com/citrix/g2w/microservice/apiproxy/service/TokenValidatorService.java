package com.citrix.g2w.microservice.apiproxy.service;

import com.citrix.g2w.microservice.apiproxy.dto.AuthenticationToken;

/**
 * Created by Gaurav on 03/08/15.
 */
public interface TokenValidatorService {

    public AuthenticationToken getResponse(String token);
}
