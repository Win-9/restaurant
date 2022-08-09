package com.example.restaurant.wishlist.repository;

import com.example.restaurant.repository.MemoryDbEntity;
import com.example.restaurant.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class WishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;

    private WishListEntity create(){
        WishListEntity wishListEntity = new WishListEntity();
        wishListEntity.setTitle("title");
        wishListEntity.setCategory("category");
        wishListEntity.setAddress("address");
        wishListEntity.setRoadAddress("readAddress");
        wishListEntity.setHomePageLink("");
        wishListEntity.setImageLink("");
        wishListEntity.setVisit(false);
        wishListEntity.setVisitCount(0);
        wishListEntity.setLastVisitDate(null);

        return wishListEntity;
    }

    @Test
    public void saveTest(){
        WishListEntity wishListEntity = create();
        MemoryDbEntity savedEntity = wishListRepository.save(wishListEntity);

        Assertions.assertNotNull(savedEntity);
        Assertions.assertEquals(1, savedEntity.getIndex());

    }

    @Test
    public void updateTest(){
        WishListEntity wishListEntity = create();
        WishListEntity savedEntity = wishListRepository.save(wishListEntity);

        savedEntity.setTitle("update test");
        WishListEntity reSavedEntity = wishListRepository.save(savedEntity);

        Assertions.assertEquals("update test", reSavedEntity.getTitle());
        Assertions.assertEquals(1, wishListRepository.findAll().size());

    }

    @Test
    public void findByIdTest(){
        WishListEntity wishListEntity = create();
        wishListRepository.save(wishListEntity);

        Optional<WishListEntity> findEntity = wishListRepository.findById(1);

        Assertions.assertEquals(true, findEntity.isPresent());
        Assertions.assertEquals(1, findEntity.get().getIndex());
    }

    @Test
    public void deleteTest(){
        WishListEntity wishListEntity = create();
        wishListRepository.save(wishListEntity);

        wishListRepository.deleteById(1);

        int size = wishListRepository.findAll().size();
        Assertions.assertEquals(0, size);

    }

    @Test
    public void listAllTest(){
        WishListEntity wishListEntity1 = create();
        wishListRepository.save(wishListEntity1);

        WishListEntity wishListEntity2 = create();
        wishListRepository.save(wishListEntity2);

        int size = wishListRepository.findAll().size();
        Assertions.assertEquals(2, size);
    }
}
