package com.musinsa.musinsabyjunior.domain;

import com.musinsa.musinsabyjunior.constant.Role;
import com.musinsa.musinsabyjunior.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Table(name="member")
@Entity
public class Member {

    @Column(name="member_key")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String memberId;  // 회원 아이디

    @Column(unique = true)
    private String email;  // 이메일

    private String password;  // 비밀번호

    private String address;  // 주소

    @Enumerated(EnumType.STRING)

    private Role role;  // 권한구분

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {

        Member member = new Member();
        member.setMemberId(memberFormDto.getMemberId());
        member.setEmail(member.getEmail());
        member.setAddress(member.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }
}

