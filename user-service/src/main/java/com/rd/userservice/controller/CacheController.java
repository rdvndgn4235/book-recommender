package com.rd.userservice.controller;

import com.rd.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cache")
public class CacheController {
    private final UserService userService;

    public CacheController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<Void> clearCache() {
        userService.clearAllCache();
        return ResponseEntity.ok().build();
    }

}
