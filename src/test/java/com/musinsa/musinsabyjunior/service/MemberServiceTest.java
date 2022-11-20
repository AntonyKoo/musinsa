package com.musinsa.musinsabyjunior.service;

import com.musinsa.musinsabyjunior.domain.Member;
import com.musinsa.musinsabyjunior.dto.MemberFormDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    PasswordEncoder passwordEncoder;  // 이 친구는 왜 빨간 줄이 그어지나? 이거때문에 테스트 실패함,,
    // @Autowired 제외하니깐 빨간 줄이 사라짐,, 왜?????!!


    // 멤버 엔티티가 먼저 어떤 역할을 하는지 아니?
    // 클라에서 유저가 입력한 데이터를 받아서 처리하여 해당 객체를 생성함

    public Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setMemberId("유니콘_캐롤");
        memberFormDto.setEmail("test@naver.com");
        memberFormDto.setAddress("서욽특별시 마포구 합정동");
        memberFormDto.setPassword("1234");
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() {
        Member member = createMember();
        Member savedMember = memberService.saveMember(member);

        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getMemberId(), savedMember.getMemberId());
        assertEquals(member.getAddress(), savedMember.getAddress());
        assertEquals(member.getPassword(), savedMember.getPassword());
        assertEquals(member.getRole(), savedMember.getRole());
    }

    @Test
    @DisplayName("중복 회원가입 테스트")
    public void saveDuplicateMemberTest() {
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member2);
        });

        assertEquals("이미 가입된 회원입니다.", e.getMessage());
        }
    }