package com.example.deviceapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/members")
    public String membersPage() {
        return "members";     // templates/members.html 필요
    }
}
