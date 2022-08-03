package com.example.restaurant.wishlist.service;


import com.example.restaurant.wishlist.dto.WishListDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListServiceTest {

    @Autowired
    private WishListService wishListService;

    @Test
    public void searchTest(){
        WishListDto result = wishListService.search("햄버거");

        System.out.println("result = " + result);
        Assertions.assertNotNull(result);
    }
}
