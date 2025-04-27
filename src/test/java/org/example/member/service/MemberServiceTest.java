package org.example.member.service;

import jakarta.transaction.Transactional;
import org.example.member.domain.Member;
import org.example.member.dto.SignUpRequest;
import org.example.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void signUp_success() {
        SignUpRequest request = new SignUpRequest("test@example.com",
                                                  "password123",
                                                  "testUser");

        Long memberId = memberService.signUp(request);

        Member member = memberRepository.findById(memberId).orElseThrow();

        assertNotNull(member.getPassword());
        assertEquals("testUser", member.getName());

        assertTrue(passwordEncoder.matches(request.getPassword(), member.getPassword()));
    }
}
