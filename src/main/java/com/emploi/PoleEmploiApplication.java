package com.emploi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.emploi.service.InitService;

@ComponentScan
@SpringBootApplication
public class PoleEmploiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PoleEmploiApplication.class, args);
	}
	
	@Autowired
	InitService initService;
	  

    @Override
    public void run(String... arg0) throws Exception {
    	initService.init();
    }	  	
}