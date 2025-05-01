package org.example.member.service;

import org.example.member.domain.Member;
import org.example.member.dto.SignUpRequest;
import org.example.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Long signUp(SignUpRequest request) {

        if (memberRepository.findByName(request.getName()).isPresent()) {
            throw new IllegalArgumentException("Username is already in use");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Member member = new Member(request.getEmail(),
                                   encodedPassword,
                                   request.getName());

        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }
}
