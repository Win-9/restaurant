package com.example.restaurant.naver.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageRes {
    private String lastBuildDate;
    private String total;
    private String start;
    private String display;
    private List<SearchImageItem> items;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class SearchImageItem{
        private String title;
        private String link;
        private String thumbnail;
        private String sizeheight;
        private String sizewidth;
    }
}
