package com.musinsa.musinsabyjunior.repository;

import com.musinsa.musinsabyjunior.constant.ItemSaleStatus;
import com.musinsa.musinsabyjunior.domain.Item;
import com.musinsa.musinsabyjunior.domain.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class ItemRepositoryTest {

    @PersistenceContext
    EntityManager em;

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
            item.setItemDetail("테스트상품상세설명" + i);
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

    @Test
    @DisplayName("상품명, 상품상세설명 or 조건으로 조회하는 테스트")
    public void findByItemNmOrItemDetailTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트상품이름1", "테스트상품상세설명5");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005L);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderbyPricedesc() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005L);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetail("테스트상품상세설명");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
    public void findByitemDetailByNativeTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetailByNative("테스트상품상세설명");
        for(Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("Querydsl 조회 테스트1")
    public void queryDslTest() {
        this.createItemList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSaleStatus.eq(ItemSaleStatus.ON_SALE))
                .where(qItem.itemDetail.like("%"+"테스트상품상세설명"+"%"))
                .orderBy(qItem.price.desc());

        List<Item> itemList = query.fetch();

        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    public void createItemList2() {
        for(int i=1; i<=5; i++){
            Item item = new Item();
            item.setItemNm("테스트상품"+i);
            item.setPrice(10000L + i);
            item.setItemDetail("테스트상품상세설명"+i);
            item.setItemSaleStatus(ItemSaleStatus.ON_SALE);
            item.setStockNumber(100L);
            item.setCreatedAt(LocalDateTime.now());
            item.setModifiedAt(LocalDateTime.now());
            itemRepository.save(item);
        }

        for(int i=6; i<=10; i++){
            Item item = new Item();
            item.setItemNm("테스트상품"+i);
            item.setPrice(10000L+i);
            item.setItemDetail("테스트상품상세설명"+i);
            item.setItemSaleStatus(ItemSaleStatus.SOLD_OUT);
            item.setStockNumber(0L);
            item.setCreatedAt(LocalDateTime.now());
            item.setModifiedAt(LocalDateTime.now());
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품 Querydsl 조회테스트2")
    public void queryDslTest2() {

        this.createItemList2();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;
        String itemDetail = "테스트상품상세설명";
        Long price = 10003L;
        String itemSaleStat = "ON_SALE";

        booleanBuilder.and(item.itemDetail.like("%"+itemDetail+"%"));
        booleanBuilder.and(item.price.gt(price));

        if(StringUtils.equals(itemSaleStat, ItemSaleStatus.ON_SALE)){
            booleanBuilder.and(item.itemSaleStatus.eq(ItemSaleStatus.ON_SALE));
        }

        Pageable pageable = PageRequest.of(0,5);
        Page<Item> itemPagingResult =
                itemRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements : " +
                itemPagingResult.getTotalElements());

        List<Item> resultItemList = itemPagingResult.getContent();
        for(Item resultItem : resultItemList){
            System.out.println(resultItem.toString());
        }
    }
}