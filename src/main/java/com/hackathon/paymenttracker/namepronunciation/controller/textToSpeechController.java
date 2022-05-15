package com.hackathon.paymenttracker.namepronunciation.controller;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.util.ByteArrayBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hackathon.paymenttracker.namepronunciation.model.TextToSpeech;

@RestController
public class textToSpeechController {

	@Autowired
	RestTemplate restTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(textToSpeechController.class);
	static String key = "c70c20199fb0429992ac543f60f5fc3e";
	static String tokenEndpoint = "https://eastus.api.cognitive.microsoft.com/sts/v1.0/issuetoken";
	static String serviceEndpoint = "https://eastus.tts.speech.microsoft.com/cognitiveservices/v1";

	@InitBinder
	public void populateNameRequest(WebDataBinder binder) {
		binder.setDisallowedFields(new String[] {});
	}

	@PostMapping(value = "/api/token")
	public String GetToken() {

		Map<String, String> headers = new HashMap<>();
		HttpHeaders header = new HttpHeaders();
		header.set("Ocp-Apim-Subscription-Key", key);
		header.set("Content-Type", "application/x-www-form-urlencoded");
		HttpEntity<String> request = new HttpEntity<String>("", header);
		String response = restTemplate.postForObject(tokenEndpoint, request, String.class);
		return response;
	}

	@PostMapping(value = "/api/pronounce")
	public byte[] GetAudio(@RequestBody TextToSpeech nameRequest) {
		String content = "<speak version='1.0' xml:lang='en-US'><voice xml:lang='en-US' xml:gender='Male' name='en-US-ChristopherNeural'>"+nameRequest.getName()+"</voice></speak>";
		
		HttpHeaders header = new HttpHeaders();
		header.set("Authorization", nameRequest.getToken());
		header.set("Content-Type", "application/ssml+xml");
		header.set("X-Microsoft-OutputFormat", "audio-16khz-64kbitrate-mono-mp3");
		HttpEntity<String> request = new HttpEntity<String>(content, header);
		byte[] response = Base64.encodeBase64(restTemplate.postForObject(serviceEndpoint, request, byte[].class));
		//return new String(response, StandardCharsets.UTF_8);
		return response;
	}

}
