package com.citrix.g2w.microservice.api.dto;

/**
 * Represents authentication response.
 *
 * Created by Gaurav on 31/07/15.
 */
public class AuthenticationResponse {
    private Long organizerKey;
    private Long accountKey;
    private String token;
    private String method;

    public Long getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(Long accountKey) {
        this.accountKey = accountKey;
    }

    public Long getOrganizerKey() {
        return organizerKey;
    }

    public void setOrganizerKey(Long organizerKey) {
        this.organizerKey = organizerKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
