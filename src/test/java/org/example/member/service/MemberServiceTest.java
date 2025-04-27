package org.example.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void signUp_success() {
        SignUpRequest request = new SignUpRequest("test@example.com",
                                                  "passord123",
                                                  "testUser");

        Long memberId = memberService.signUp(request);

        Member member = memberRepository.findById(memberId).orElseThrow();
        assertEquals("test@example.com", member.getEmail());
        assertNotNull(member.getPassword());
        assertEquals("테스트유저", member.getName());
    }
}
