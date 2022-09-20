package com.musinsa.musinsabyjunior.controller;

import com.musinsa.musinsabyjunior.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/thymeleaf")
public class ThymeleafExController {

    @GetMapping(value="/ex01")
    public String thymeleafExample01(Model model) {
        model.addAttribute("data","타임리프 예제 입니다.");
        return "thymeleafEx/thymeleafEx01";
    }

    @GetMapping(value="/ex02")
    public String thymeleafExample02(Model model) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemNm("테스트상품1");
        itemDto.setItemDetail("테스트상품상세설명");
        itemDto.setPrice(10000L);
        itemDto.setCreatedAt(LocalDateTime.now());

        model.addAttribute("itemDto", itemDto);
        return "thymeleafEx/thymeleafEx02";
    }

    @GetMapping(value="/ex03")
    public String thymeleafExample03(Model model) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        for(int i=1; i<=10; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemNm("테스트상품" + i);
            itemDto.setItemDetail("테스트상품상세설명" + i);
            itemDto.setPrice(10000L * i);
            itemDto.setCreatedAt(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList", itemDtoList);
        return "thymeleafEx/thymeleafEx03";
    }

    @GetMapping(value="/ex04")
    public String thymeleafExample04(Model model) {
        List<ItemDto> itemDtoList = new ArrayList<>();

        for(int i=1; i<=10; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemNm("테스트상품" + i);
            itemDto.setItemDetail("테스트상품상세설명" + i);
            itemDto.setPrice(10000L * i);
            itemDto.setCreatedAt(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList", itemDtoList);
        return "thymeleafEx/thymeleafEx04";
    }

    @GetMapping(value="/ex05")
    public String thymeleafExample05(Model model) {
        return "thymeleafEx/thymeleafEx05";
    }
}


