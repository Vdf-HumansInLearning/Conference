package com.vodafone.conference.api.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @GetMapping("/")
    public String index() {
        //return "Home Page";
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome user</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome admin</h1>");
    }
}
