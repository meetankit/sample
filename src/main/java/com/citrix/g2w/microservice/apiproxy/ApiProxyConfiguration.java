package com.citrix.g2w.microservice.apiproxy;

import com.citrix.g2w.microservice.apiproxy.service.TokenValidatorServiceImpl;
import com.google.common.collect.Lists;

import org.springframework.cloud.netflix.zuul.ZuulConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Gaurav on 31/07/15.
 */
@Configuration
public class ApiProxyConfiguration extends ZuulConfiguration {

    /**
     * Token validator service
     * @return {@link TokenValidatorServiceImpl}
     */
    @Bean
    public TokenValidatorServiceImpl tokenValidatorService() {
        TokenValidatorServiceImpl tokenValidatorService = new TokenValidatorServiceImpl();
        tokenValidatorService.setRestTemplate(jsonRestTemplate());
        return tokenValidatorService;
    }

    /**
     * rest template
     * @return {@link RestTemplate}
     */
    @Bean
    public RestTemplate jsonRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(httpRequestFactory());
        List<HttpMessageConverter<?>> messageConverters = Lists.newArrayList();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    /**
     * request factory
     * @return {@link SimpleClientHttpRequestFactory}
     */
    @Bean
    public SimpleClientHttpRequestFactory httpRequestFactory() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(5000);
        requestFactory.setConnectTimeout(5000);
        return requestFactory;
    }
}
