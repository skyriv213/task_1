package com.example.shop.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor
public class ItemDto {
    private String title;
    private String link;
    private String image;
    private int lprice;
public ItemDto(JSONObject ItemJson ){
    this.title = ItemJson.getString("title");
    this.link = ItemJson.getString("link");
    this.image = ItemJson.getString("image");
    this.lprice = ItemJson.getInt("lprice");
}

}
