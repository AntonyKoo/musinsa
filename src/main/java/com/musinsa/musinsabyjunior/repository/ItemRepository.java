package com.musinsa.musinsabyjunior.repository;

import com.musinsa.musinsabyjunior.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemNm(String itemNm);
}
