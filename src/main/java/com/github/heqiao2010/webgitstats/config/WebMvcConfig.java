package com.github.heqiao2010.webgitstats.config;

import com.github.heqiao2010.webgitstats.service.WebGitStatsUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/stats/**")
                .addResourceLocations("file:" + WebGitStatsUtils.getStatsPath() + File.separator);
    }

}
