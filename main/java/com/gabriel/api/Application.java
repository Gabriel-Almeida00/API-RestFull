package com.gabriel.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		Pbkdf2PasswordEncoder pbkdf2Encoder =
				new Pbkdf2PasswordEncoder("",8, 185000,
						Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

		/*Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("pbkdf2",pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

		String result = passwordEncoder.encode("admin234");
		String result1 = passwordEncoder.encode("gabriel1234");
		System.out.println("My hash " + result);
		System.out.println("My hash " + result1);*/
	}
}
