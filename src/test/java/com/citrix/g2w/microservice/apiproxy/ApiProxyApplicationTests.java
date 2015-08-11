package com.citrix.g2w.microservice.apiproxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiProxyApplication.class)
@WebAppConfiguration
public class ApiProxyApplicationTests {

	@Test
	public void testClassConfiguration() {
		ApiProxyApplication application = new ApiProxyApplication();
		Class c = application.getClass();
		Boolean hasAnnotation = false;
		Annotation annotations[] = c.getAnnotations();
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
