package com.example.shop.service;

import com.example.shop.dtos.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NaverApiService {

    public List<ItemDto> searchItems(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "Ej6bAhGJBvIRxGMXQmaH");
        headers.add("X-Naver-Client-Secret", "37Z8JXDZkC");
        String body = "";
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?display=15&query=" + query,
                HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        log.info("NAVER API Status Code : " + status);
        String response = responseEntity.getBody();

        return fromJSONONtoItems(response);
    }

    private List<ItemDto> fromJSONONtoItems(String response) {
        // json object를 response에서 받아와서 작성
        JSONObject rjson = new JSONObject(response);
        // json배열의 key값이 item인 부분을 받아온다.
        JSONArray items = rjson.getJSONArray("items");
        ArrayList<ItemDto> itemDtoList = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            JSONObject itemsJSON = items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(itemsJSON);
            itemDtoList.add(itemDto);
        }
        return itemDtoList;
    }
}
