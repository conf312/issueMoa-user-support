package com.issuemoa.user.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class IssuemoaUserSupportApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssuemoaUserSupportApplication.class, args);
	}

}
