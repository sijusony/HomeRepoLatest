package org.homedepo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;


@Configuration
public class PropertiesUtil {

	@Autowired
    private Environment environment;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   builder.setConnectTimeout(2000);
	   return builder.build();
	}

	public String getRedskyURL() {
        return environment.getProperty("redsky.url");       
    }
	
	public String loginUser() {
        return environment.getProperty("login.user");       
    }
	
	public String getLoginPassword() {
        return environment.getProperty("login.pswd");       
    }
	
	
	
}
