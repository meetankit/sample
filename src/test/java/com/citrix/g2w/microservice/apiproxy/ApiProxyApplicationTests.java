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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test for ApiProxyApplication
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiProxyApplication.class)
@WebAppConfiguration
public class ApiProxyApplicationTests {

	/**
	 * Checks for EnableZuulProxy annotation.
	 */
	@Test
	public void testClassAnnotation() {
		ApiProxyApplication application = new ApiProxyApplication();
		Class cls = application.getClass();
		Boolean hasAnnotation = false;
		Annotation annotations[] = cls.getAnnotations();
		for(Annotation annotation : annotations) {
			if(annotation.toString().equals("@org.springframework.cloud.netflix.zuul.EnableZuulProxy()")) {
				hasAnnotation = true;
			}
		}
		if(hasAnnotation == false) {
			fail("Not have EnableZuulProxy annotation");
		}
		application.main(new String[]{});
		assertTrue(true);
	}
}
