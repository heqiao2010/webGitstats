package com.github.heqiao2010.webgitstats;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class HealthController {

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String health() throws InterruptedException{
		TimeUnit.SECONDS.sleep(10);
		return "hello world!";
	}
}
