package com.citrix.g2w.microservice.Service;

import com.citrix.g2w.microservice.api.dto.AuthenticationToken;

/**
 * Created by Gaurav on 03/08/15.
 */
public interface TokenValidatorService {

    public AuthenticationToken getResponse(String token);
}
