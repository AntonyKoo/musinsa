package com.musinsa.musinsabyjunior.repository;

import com.musinsa.musinsabyjunior.constant.ItemSaleStatus;
import com.musinsa.musinsabyjunior.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품저장테스트")
    public void createItemTest() {
        Item item = new Item();
        item.setItemNm("테스트상품");
        item.setPrice(10000L);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSaleStatus(ItemSaleStatus.ON_SALE);
        item.setStockNumber(100L);
        item.setCreatedAt(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());

    }

    public void createItemList() {
        for (int i=1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트상품이름" + i);
            item.setPrice(10000L + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSaleStatus(ItemSaleStatus.ON_SALE);
            item.setStockNumber(100L);
            item.setCreatedAt(LocalDateTime.now());
            item.setModifiedAt(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }
    
    @Test
    @DisplayName("상품명 조회 테스트")
    public void findbyItemNmTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트상품이름1");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

}