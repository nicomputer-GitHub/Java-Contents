package com.example.linebot.data;

import com.example.linebot.service.JankenResponse;

import org.springframework.core.io.Resource;

import org.springframework.stereotype.Repository;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Repository
public class JankenAPI {
    // EC2の画像の送付先　application.properties ファイルから読み込まれる。
    @Value("${janken.api.url}")
    private String API_URL;

    // Springの機能を使って、HTTPの要求メッセージを作成。
    private final RestTemplate restTemplate;

    public JankenAPI(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(30)).setReadTimeout(Duration.ofSeconds(30)).build();
    }

    public JankenResponse playGame(Resource imageResource){
        HttpHeaders formHeaders = new HttpHeaders();
        formHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", imageResource);

        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(map, formHeaders);
        ResponseEntity<JankenResponse> response = restTemplate.postForEntity(API_URL, formEntity, JankenResponse.class);

        System.out.println(response.getBody().toString());
        return response.getBody();
    }
}
