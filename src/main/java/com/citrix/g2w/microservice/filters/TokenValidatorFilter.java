package com.citrix.g2w.microservice.filters;

import com.citrix.g2w.microservice.Service.TokenValidatorService;
import com.citrix.g2w.microservice.api.dto.AuthenticationToken;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Gaurav on 04/08/15.
 */
@Component
public class TokenValidatorFilter extends ZuulFilter {

    @Autowired
    private TokenValidatorService tokenValidatorService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 5;
    }

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
        AuthenticationToken authenticationToken = tokenValidatorService.getResponse(token);
        ctx.addZuulRequestHeader("userKey", authenticationToken.getUserKey().toString());
        StringBuilder grantedAuthorities = new StringBuilder();
        for(String authorities : authenticationToken.getGrantedAuthorities()) {
            grantedAuthorities.append(", ").append(authorities);
        }
        ctx.addZuulRequestHeader("grantedAuthorities", grantedAuthorities.substring(2));
        return null;
    }
}
