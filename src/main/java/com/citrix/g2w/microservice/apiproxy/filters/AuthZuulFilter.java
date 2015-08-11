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
package com.citrix.g2w.microservice.apiproxy.filters;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;

import com.citrix.g2w.microservice.apiproxy.dto.AuthenticationToken;
import com.citrix.g2w.microservice.apiproxy.service.AuthClientService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Filter for doing Authentication.
 *
 * Created by Gaurav on 04/08/15.
 */
@Component
public class AuthZuulFilter extends ZuulFilter {

    @Autowired
    private AuthClientService authClientService;

    /**
     * Represent type of filter.
     * @return type of filter
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * Represent order of execution of this filter.
     * @return filter order
     */
    @Override
    public int filterOrder() {
        return 5;
    }

    /**
     * Checks condition to execute run or not.
     * @return boolean value to execute run or not.
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if(request.getHeader("token") != null) {
            return true;
        }
        return false;
    }

    /**
     * if shouldFilter() is true, this method will be invoked.
     * @return Object
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader("token");
        try {
            AuthenticationToken authenticationToken = authClientService.verifyToken(token);
            ctx.addZuulRequestHeader("userKey", authenticationToken.getUserKey().toString());
            StringBuilder grantedAuthorities = new StringBuilder();
            for (String authorities : authenticationToken.getGrantedAuthorities()) {
                grantedAuthorities.append(", ").append(authorities);
            }
            ctx.addZuulRequestHeader("roles", grantedAuthorities.substring(2));
        } catch (RestClientException re) {
            ctx.setSendZuulResponse(false);
            try {
                (new ProxyRequestHelper()).setResponse(org.apache.http.HttpStatus.SC_UNAUTHORIZED, null, new LinkedMultiValueMap<String, String>());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
