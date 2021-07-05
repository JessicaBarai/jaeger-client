package com.techprimers.jaegerclient;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class JaegerClientApplication {
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
		
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@RequestMapping("/")
	public String welcome() throws JSONException {
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("Message", "Hello from client");
		return jsonObject.toString();
		
	}
	@RequestMapping("/server")
	public String fromServer() throws RestClientException, JSONException {
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("Message from server to client", restTemplate.exchange(
				"http://jaeger-server-git:8082", HttpMethod.GET, null, String.class).getBody());
		return jsonObject.toString();
		
		
		
	}

	public static void main(String[] args) {
		System.out.print("STARTING WITH PROPERTIES CHANGED!");
		SpringApplication.run(JaegerClientApplication.class, args);
	}

}
