package com.rd.scm;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.rd.scm.service.TestConnectService;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

/*
 * The main does not have access to the services because it is static and class scope, not object scope. So we implement CommandLineRunner interface
 * After application is started and before it processes any web requests, spring runs the Run method and passes in any commandline arguments.
 */
@SpringBootApplication
@EnableEncryptableProperties
public class ScmApplication implements CommandLineRunner {

	@Autowired
	private TestConnectService testConnectService;

	public static void main(String[] args) {
		SpringApplication.run(ScmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// RespondStatus b = testConnectService.TestConnectEnvironment("d12g");
		// System.out.println("=====================>" + b.getErrorText());

	}

	// This is another way to handle internationalization by passing a locale in the
	// header of the request
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource bundleMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

}
