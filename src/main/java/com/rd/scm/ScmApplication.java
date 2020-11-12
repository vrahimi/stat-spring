package com.rd.scm;

import com.rd.scm.datasource.DSConfig;
import com.rd.scm.service.TestConnectService;
import com.rd.scm.util.RespondStatus;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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

        //RespondStatus b = testConnectService.TestConnectEnvironment("d12g");
        //System.out.println("=====================>" + b.getErrorText());
	
	}

	

}
