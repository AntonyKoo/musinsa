package com.musinsa.musinsabyjunior.repository;

import com.musinsa.musinsabyjunior.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
