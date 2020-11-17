package com.rd.scm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
