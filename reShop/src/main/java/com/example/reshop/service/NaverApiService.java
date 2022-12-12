package com.example.reshop.service;

import com.example.reshop.dtos.ItemDto;

import java.util.List;

public interface NaverApiService {

    List<ItemDto> searchItems(String query);

    List<ItemDto> fromJSONtoItems(String response);

}
