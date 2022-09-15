package com.musinsa.musinsabyjunior.repository;

import com.musinsa.musinsabyjunior.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
