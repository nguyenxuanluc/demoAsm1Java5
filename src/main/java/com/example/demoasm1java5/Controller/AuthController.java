package com.example.demoasm1java5.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String showloginPage() {
        return "login";
    }
    @PostMapping("/login")
    public String  handlogin(@RequestParam String username, @RequestParam String password, @RequestParam String remember) {

        System.out.println("username: " + username + " password: " + password + " remember: " + remember);

        return "index";
    }
}
