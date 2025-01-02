package com.turgaydede.util;

import com.turgaydede.dto.RestTemplateRequest;
import jakarta.annotation.Nullable;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Component
public class RestClient {

    private final RestTemplate restTemplate;
    @Setter
    private RestTemplateRequest restTemplateRequest;

    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> postForEntity(Object requestBody, Class<T> responseType, MediaType mediaType) {
        HttpHeaders headers = createHeaders();
        headers.setContentType(mediaType);
        HttpEntity<T> requestHttp = new HttpEntity<>((T) requestBody, headers);
        return restTemplate.postForEntity(restTemplateRequest.getUrl(), requestHttp, responseType);
    }

    public <T> ResponseEntity<T> postForEntity(Object requestBody, Class<T> responseType, String token, MediaType mediaType) {
        HttpHeaders headers = createHeaders(token);
        headers.setContentType(mediaType);
        HttpEntity<T> requestHttp = new HttpEntity<>((T) requestBody, headers);
        return restTemplate.postForEntity(restTemplateRequest.getUrl(), requestHttp, responseType);
    }

    public <T> T exchange(@Nullable MultiValueMap<Object, Object> formData, Class<T> responseType) {
        return restTemplate.exchange(restTemplateRequest.getUrl(), HttpMethod.POST, new HttpEntity<>(formData, createHeaders()), responseType).getBody();
    }


    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String auth = String.format("%s:%s",restTemplateRequest.getUsername(),restTemplateRequest.getPassword());
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        return headers;
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        String authHeader = "Bearer " + token;
        headers.set("Authorization", authHeader);
        return headers;
    }
}
