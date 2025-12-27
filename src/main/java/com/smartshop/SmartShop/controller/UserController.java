package com.smartshop.SmartShop.controller;
import com.smartshop.SmartShop.dto.UserRegisterDTO;
import com.smartshop.SmartShop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody UserRegisterDTO dto) {

        userService.registerUser(dto);
        return ResponseEntity.ok("User registered successfully");
    }
}
