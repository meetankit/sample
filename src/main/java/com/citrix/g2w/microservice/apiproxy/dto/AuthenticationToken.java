package com.citrix.g2w.microservice.apiproxy.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by Gaurav on 03/08/15.
 */
public class AuthenticationToken {

    private Long userKey;
    private String token;
    private Date expirationDate;
    private String developerToken;
    private List<String> grantedAuthorities;

    public String getDeveloperToken() {
        return developerToken;
    }

    public void setDeveloperToken(String developerToken) {
        this.developerToken = developerToken;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserKey() {
        return userKey;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    public List<String> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(List<String> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }
}
