package com.tilak.crudWithMapping.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Testcontroller {

    @GetMapping("/admin")
    public String adminAccess() {
        return "This endpoint is accessible only by ADMIN role.";
    }

    @GetMapping("/user")
    public String userAccess() {
        return "This endpoint is accessible by USER and ADMIN roles.";
    }

    @GetMapping("/public")
    public String publicAccess() {
        return "This endpoint is accessible by anyone.";
    }
}
