package com.example.java1120;

import com.example.java1120.domain.Role;
import com.example.java1120.repository.MemberRepository;
import com.example.java1120.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Test
    @DisplayName("Member 데이터 생성")
    void save() {

        // admin 계정
        // given
        Member admin = new Member();
        admin.setId("admin");
        admin.setName("admin");
        admin.setRole(Role.ADMIN);
        admin.setPassword(passwordEncoder.encode("1234"));

        // when
        Member savedMember1 = memberRepository.save(admin);
        // then
        assertEquals(admin.getName(), savedMember1.getName());


        //member 계정
        Member member = new Member();
        member.setId("member");
        member.setName("member");
        member.setRole(Role.MEMBER);
        member.setPassword(passwordEncoder.encode("1234"));
        // when
        Member savedMember2 = memberRepository.save(member);
        // then
        assertEquals(member.getName(), savedMember2.getName());


        // manager 계정
        // given
        Member manager = new Member();
        manager.setId("manager");
        manager.setName("manager");
        manager.setRole(Role.MANAGER);
        manager.setPassword(passwordEncoder.encode("1234"));

        // when
        Member savedMember3 = memberRepository.save(manager);
        // then
        assertEquals(manager.getName(), savedMember3.getName());
    }
}