package com.rd.scm.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/*
https://dzone.com/articles/packaging-springboot-application-with-external-dep
https://dzone.com/articles/spring-boot-profiles-1
https://www.infoworld.com/article/3543268/junit-5-tutorial-part-2-unit-testing-spring-mvc-with-junit-5.html

https://lankydan.dev/2019/01/09/configuring-logback-with-spring-boot
https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#web-properties
*/

@Configuration
@ConfigurationProperties(prefix="message") 
@PropertySource("classpath:messages_en.properties")
public class MessageConfigProperties {
	
	private String greetings;

	public String getGreetings() {
		return greetings;
	}

	public void setGreetings(String greetings) {
		this.greetings = greetings;
	}
	
}
