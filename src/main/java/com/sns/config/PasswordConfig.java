package com.sns.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.sns.common.MD5Encoder;

@Configuration
public class PasswordConfig {
	@Bean
	@Qualifier("BCrypt")
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Qualifier("SCrypt")
	public PasswordEncoder encoder2() {
		return new SCryptPasswordEncoder();
	}
	
	
	// This gives unsafe encryption. Should never use for serious programs.
	@Bean
	@Qualifier("MD5")
	public PasswordEncoder unsafeEncoder() {
		return new MD5Encoder();
	}
}
