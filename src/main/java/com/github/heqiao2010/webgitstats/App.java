package com.github.heqiao2010.webgitstats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * the starter!
 *
 */
@EnableAutoConfiguration
@ComponentScan("com.github.heqiao2010.webgitstats")
@EnableAspectJAutoProxy
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
