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
package com.citrix.g2w.microservice.apiproxy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableZuulProxy
@SpringBootApplication
@RestController
public class ApiProxyApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello.. Welcome to Home Page";
    }

    @RequestMapping(value = "/authToken")
    public Map<String, String> verifyToken(@RequestHeader("token") String token,
                            @RequestHeader("userKey") String userKey,
                            @RequestHeader("roles") String grantedAuthorities) {
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("userKey", userKey);
        response.put("roles", grantedAuthorities);
        return response;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiProxyApplication.class, args);
    }
}
