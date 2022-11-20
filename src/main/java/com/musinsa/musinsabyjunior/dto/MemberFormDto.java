package com.musinsa.musinsabyjunior.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFormDto {
    private String memberId;
    private String email;
    private String password;
    private String address;
}
