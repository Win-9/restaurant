package com.example.restaurant.naver;


import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaveClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocalTest(){
        SearchLocalReq search = new SearchLocalReq();
        search.setQuery("햄버거");

        SearchLocalRes result = naverClient.searchLocal(search);
        System.out.println("result = " + result);
    }

    @Test
    public void searchImageTest(){
        SearchImageReq searchImageReq = new SearchImageReq();
        searchImageReq.setQuery("햄버거");

        SearchLocalRes result = naverClient.searchImage(searchImageReq);

        System.out.println("result = " + result);
    }


}
