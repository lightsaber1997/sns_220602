package com.sns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.sns.common.AlphaNumericRandomStringGenerator;
import com.sns.common.RandomStringGenerator;

import java.security.SecureRandom;
import java.util.Random;

@Configuration
public class RandomGeneratorConfig {
    @Bean
    public Random randomGenerator() {
        return new SecureRandom();
    }
    
    @Bean
    public RandomStringGenerator rsg(Random random) {
    	return new AlphaNumericRandomStringGenerator(random);
    }


}