package org.example.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.member.dto.SignUpRequest;
import org.example.member.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequest request) {
        memberService.signUp(request);
        return "success";
    }
}
