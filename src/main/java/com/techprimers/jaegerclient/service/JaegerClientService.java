package com.techprimers.jaegerclient.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class JaegerClientService {

  private WebClient webClient;

  public JaegerClientService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<String> get(Integer id) {

    return webClient.get()
        .uri("http://jaeger-server-git-edb.apps.aro.euw-hub03.azure.volvo.net/" + id)
        .retrieve()
        .bodyToMono(String.class);
  }
}
