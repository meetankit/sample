package com.citrix.g2w.microservice;

import com.citrix.collaboration.domain.License;
import com.citrix.collaboration.domain.Organizer;
import com.citrix.collaboration.services.account.CollaborationAccountService;
import com.citrix.g2w.microservice.api.dto.AuthenticationRequest;
import com.citrix.g2w.microservice.api.dto.AuthenticationResponse;
import com.citrix.jmx.monitor.Monitor;
import com.citrix.security.authentication.RestAuthentication;
import com.citrix.security.authentication.RestAuthenticationTokenService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

@EnableZuulProxy
@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private RestAuthenticationTokenService restAuthenticationTokenService;

    @Autowired
    private CollaborationAccountService collaborationAccountService;

    private String authorizationSchemeName = "User";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        return "Hello world";
    }

    @RequestMapping("/")
    public String home() {
        return "Hello.. Welcome to Home Page";
    }

    @RequestMapping(value = "/authToken", method = RequestMethod.POST)
    @ResponseBody
    @Monitor
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest authRequest) {
        final String username = authRequest.getUsername();
        final String password = authRequest.getPassword();

        checkArgument(username != null, "username may not be null");
        checkArgument(password != null, "password may not be null");

        RestAuthentication restAuthentication = restAuthenticationTokenService.createToken(username, password);

        String token = (String) restAuthentication.getCredentials();
        Organizer organizer = (Organizer) restAuthentication.getDetails();

        AuthenticationResponse response = new AuthenticationResponse();
        response.setOrganizerKey(organizer.getKey());
        response.setAccountKey(getG2WAccountKey(organizer));
        response.setToken(token);
        response.setMethod(authorizationSchemeName);

        return response;
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    public Long getG2WAccountKey(Organizer organizer) {

        //If there is no list of accountkeys, return the primary account key.
        if (organizer.getAccountkeys() == null) {
            return organizer.getAccountKey();
        }

        Long accountKey = organizer.getAccountkeys().get(0);
        //if there is only one account key, use that without checking.
        if (organizer.getAccountkeys().size() == 1) {
            return accountKey;
        } else {
            List<License> licenses = collaborationAccountService.getUserLicenses(organizer.getKey(), "ROLE_G2W_ORGANIZER");

            //if the user has no Account with G2W License then return the first account Key.
            if (CollectionUtils.isNotEmpty(licenses)) {
                for (License license : licenses) {
                    if (license.getUserkeys().contains(organizer.getKey())) {
                        accountKey = license.getAccountkey();
                        break;
                    }
                }
            }
        }
        return accountKey;
    }

}
