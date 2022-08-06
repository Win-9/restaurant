package com.example.restaurant.wishlist.service;


import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchImageRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.entity.WishListEntity;
import com.example.restaurant.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;

    private final WishListRepository wishListRepository;

    public WishListDto search(String query) {

        SearchLocalReq searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        SearchLocalRes searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if (searchLocalRes.getTotal() > 0) {
            SearchLocalRes.SearchLocalItem localItem = searchLocalRes.getItems().stream().findFirst().get();

            String imageQuery = localItem.getTitle().replaceAll("<[^>]*>", "");
            SearchImageReq searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            SearchImageRes searchImageRes = naverClient.searchImage(searchImageReq);

            if (searchImageRes.getTotal() > 0) {

                SearchImageRes.SearchImageItem imageItem = searchImageRes.getItems().stream().findFirst().get();

                WishListDto result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;
            }
        }

        return new WishListDto();
    }

    public WishListDto add(WishListDto wishListDto) {
        WishListEntity entity = dtoToEntity(wishListDto);
        WishListEntity save = wishListRepository.save(entity);
        return entityToDto(save);

    }

    private WishListEntity dtoToEntity(WishListDto wishListDto){
        WishListEntity entity = new WishListEntity();
        entity.setIndex(wishListDto.getIndex());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());

        return entity;
    }

    private WishListDto entityToDto(WishListEntity wishListEntity){
        WishListDto dto = new WishListDto();
        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());

        return dto;
    }

    public List<WishListDto> findAll() {
        return wishListRepository.listAll().stream().map(it -> entityToDto(it)).collect(Collectors.toList());
    }
}
