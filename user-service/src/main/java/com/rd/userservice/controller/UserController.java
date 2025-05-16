package com.rd.userservice.controller;

import com.rd.userservice.dto.UserRequest;
import com.rd.userservice.model.User;
import com.rd.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<User> create(@RequestBody UserRequest userRequest) {
        User user = User.builder()
                        .name(userRequest.name())
                        .email(userRequest.email())
                        .favoriteGenre(userRequest.favoriteGenre())
                        .build();
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        User user = User.builder()
                        .id(id)
                        .name(userRequest.name())
                        .email(userRequest.email())
                        .favoriteGenre(userRequest.favoriteGenre())
                        .build();
        return userService.updateUser(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return userService.findById(id)
                          .map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
