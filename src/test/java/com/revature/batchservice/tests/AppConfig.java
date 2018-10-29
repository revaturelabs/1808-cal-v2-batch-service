package com.revature.batchservice.tests;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.revature.service")
@EnableJpaRepositories(basePackages = {
	"com.revature.batchservice.repository"
})
public class AppConfig {

}
