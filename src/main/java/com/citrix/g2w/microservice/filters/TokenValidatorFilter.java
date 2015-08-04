package com.citrix.g2w.microservice.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by Gaurav on 04/08/15.
 */
@Component
public class TokenValidatorFilter extends ZuulFilter {

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() { //  if shouldFilter() is true, this method will be invoked.

        RequestContext ctx = RequestContext.getCurrentContext();
        final String requestURI = this.urlPathHelper.getPathWithinApplication(ctx
                .getRequest());
        System.out.println(requestURI);
        HttpServletRequest request = ctx.getRequest();
        Enumeration<String> headers = request.getHeaderNames();
        System.out.println(headers);
        ctx.setDebugRouting(true);
        ctx.setDebugRequest(true);
        ctx.getZuulRequestHeaders();
        return null;
    }
}
