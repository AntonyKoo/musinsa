package com.musinsa.musinsabyjunior.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDto {

    private Long id;
    private String itemNm;
    private Long price;
    private String itemDetail;
    private String sellStatCd;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
