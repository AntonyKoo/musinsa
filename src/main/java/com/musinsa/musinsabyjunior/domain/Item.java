package com.musinsa.musinsabyjunior.domain;

import com.musinsa.musinsabyjunior.constant.ItemSaleStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter  // 상품엔티티에서 상품 정보에 접근
@Setter  // 상품엔티티에서 상품 정보를 수정/생성
@ToString  //
@Table(name="item")  // db에 박힐 테이블 이름을 정해줌
@Entity  // 이 클래스가 엔티티 클래스라는 것을 선언함
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;  // 상품 코드

    @Column(nullable = false, length = 50)  // default length 255인데 줄여준거임
    private String itemNm;  // 상품명

    @Column(name = "price", nullable = false)
    private Long price;  // 상품 가격

    @Column(nullable = false)
    private Long stockNumber;  // 재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;  // 상품 상세정보

    @Enumerated(EnumType.STRING)
    private ItemSaleStatus itemSaleStatus;  // 상품 판매 상태

    @CreatedDate
    private LocalDateTime createdAt;  // 상품 등록일

    @LastModifiedDate
    private LocalDateTime modifiedAt;  // 상품 관련 정보 수정일
}
