package com.musinsa.musinsabyjunior.repository;

import com.musinsa.musinsabyjunior.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>,
        QuerydslPredicateExecutor<Item> {
    
    // 상품명으로 조회하는 쿼리
    List<Item> findByItemNm(String itemNm);
    
    // Or 조건
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // LessThan 조건
    List<Item> findByPriceLessThan(Long price);

    // OrderBy 조건
    List<Item> findByPriceLessThanOrderByPriceDesc(Long price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price desc",
    nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

}
