package com.darknights.devigation.domain.admin.command.application;


import com.darknights.devigation.global.common.annotation.CurrentMember;
import com.darknights.devigation.global.security.token.UserPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping
    public String test(@CurrentMember UserPrincipal userPrincipal) {
        System.out.println("userPrincipal.getName() = " + userPrincipal.getName());
        return "test";
    }
}