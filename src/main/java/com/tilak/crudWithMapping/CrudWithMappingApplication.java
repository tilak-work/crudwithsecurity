package com.tilak.crudWithMapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
public class CrudWithMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudWithMappingApplication.class, args);
	}

}
