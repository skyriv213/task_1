package com.example.reshop.service;

import com.example.reshop.dtos.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class NaverApiServiceImpl implements NaverApiService {

    @Override
    public List<ItemDto> searchItems(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id","Ej6bAhGJBvIRxGMXQmaH");
        headers.add("X-Naver-Client-Secret", "37Z8JXDZkC");
        String body = "";
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?display=15&query=" + query , HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        log.info("Naver API Status code : " + status);
        String response = responseEntity.getBody();
        return fromJSONtoItems(response);
    }

    @Override
    public List<ItemDto> fromJSONtoItems(String response) {
        JSONObject rjson = new JSONObject(response);
        JSONArray items = rjson.getJSONArray("items");
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            JSONObject itemObject = items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(itemObject);
            itemDtoList.add(itemDto);
        }
        return itemDtoList;
    }
}
