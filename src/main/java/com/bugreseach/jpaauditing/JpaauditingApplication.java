package com.bugreseach.jpaauditing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing(modifyOnCreate = false)
@EnableJpaRepositories
public class JpaauditingApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaauditingApplication.class, args);
	}

}
