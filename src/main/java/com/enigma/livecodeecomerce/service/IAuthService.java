package com.enigma.livecodeecomerce.service;

import com.enigma.livecodeecomerce.model.User;
import com.enigma.livecodeecomerce.model.request.LoginRequest;
import com.enigma.livecodeecomerce.model.request.UserRequest;

public interface IAuthService {
    public User register(UserRequest userRequest);
    public String login(LoginRequest loginRequest);
    public void logout(String token);
}
