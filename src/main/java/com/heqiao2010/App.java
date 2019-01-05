package com.heqiao2010;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * the starter!
 *
 */
@EnableAutoConfiguration
@ComponentScan("com.heqiao2010")
@EnableAspectJAutoProxy
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
