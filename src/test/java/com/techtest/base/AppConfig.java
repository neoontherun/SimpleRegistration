package com.techtest.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = { "com.techtest" })
@PropertySource("classpath:application.properties")
public class AppConfig {
}
