package com.example.restaurant.naver.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalRes {
    private String lastBuildDate;
    private String total;
    private String start;
    private String display;
    private String category;
    private List<SearchLocalItem> items;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class SearchLocalItem{
        private String title;
        private String link;
        private String description;
        private String telephone;
        private String address;
        private String roadAddress;
        private int mapx;
        private int mapy;

    }
}
