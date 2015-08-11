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

/**
 * Created by Gaurav on 03/08/15.
 */
public interface AuthClientService {

    /**
     * Method to verify auth token.
     * @param token
     * @return AuthenticationToken
     */
    AuthenticationToken verifyToken(String token);
    
}
