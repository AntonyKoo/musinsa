package com.musinsa.musinsabyjunior.repository;

import com.musinsa.musinsabyjunior.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    
    // 상품명으로 조회하는 쿼리
    List<Item> findByItemNm(String itemNm);
    
    // Or 조건
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // LessThan 조건
    List<Item> findByPriceLessThan(Long price);

    // OrderBy 조건
    List<Item> findByPriceLessThanOrderByPriceDesc(Long price);
}
