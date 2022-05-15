package com.hackathon.paymenttracker.namepronunciation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class NamepronunciationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamepronunciationApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate template = new RestTemplate();

        template.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        return template;
	}

}
