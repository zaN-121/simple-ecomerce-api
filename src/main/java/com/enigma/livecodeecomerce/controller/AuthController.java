package com.enigma.livecodeecomerce.controller;

import com.enigma.livecodeecomerce.model.User;
import com.enigma.livecodeecomerce.model.request.LoginRequest;
import com.enigma.livecodeecomerce.model.request.UserRequest;
import com.enigma.livecodeecomerce.model.response.SuccessResponse;
import com.enigma.livecodeecomerce.service.AuthService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Getter @Setter
@NoArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRequest userRequest) {
        User user = this.authService.register(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<User>("201", "success", "berhasil register user", user));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        String token = this.authService.login(loginRequest);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return ResponseEntity.ok().body(new SuccessResponse<Map<String,String>>("201", "success", "berhasil register user", tokenMap));

    }
}
