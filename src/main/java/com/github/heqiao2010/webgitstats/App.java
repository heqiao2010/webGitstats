package com.github.heqiao2010.webgitstats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * the starter!
 *
 */
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableJpaRepositories
@EnableTransactionManagement
@ComponentScan("com.github.heqiao2010.webgitstats")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
