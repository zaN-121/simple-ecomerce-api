package com.enigma.livecodeecomerce.service;

import com.enigma.livecodeecomerce.exception.UnautorizhedException;
import com.enigma.livecodeecomerce.model.Role;
import com.enigma.livecodeecomerce.model.User;
import com.enigma.livecodeecomerce.model.UserCredential;
import com.enigma.livecodeecomerce.model.UserRole;
import com.enigma.livecodeecomerce.model.request.LoginRequest;
import com.enigma.livecodeecomerce.model.request.UserRequest;
import com.enigma.livecodeecomerce.repository.IRoleRepository;
import com.enigma.livecodeecomerce.repository.IUserRepository;
import com.enigma.livecodeecomerce.repository.IUserRoleRepository;
import com.enigma.livecodeecomerce.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@Getter @Setter
@NoArgsConstructor
public class AuthService implements IAuthService, IService<User, UserRequest>{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public User register(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        UserCredential userCredential = new UserCredential()
                .setEmail(userRequest.getEmail())
                .setUsername(userRequest.getUsername())
                .setPassword(userRequest.getPassword());

        Optional<Role> optionalRole = Optional.empty();
        user.setUserCredential(userCredential);
        try {
            optionalRole = this.roleRepository.findByName("admin");
            user = this.userRepository.save(user);

            UserRole userRole = new UserRole()
                    .setUser(user)
                    .setRole(optionalRole.get());

            userRole = this.userRoleRepository.save(userRole);
            System.out.println(userRole.toString());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return user;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Optional<User> optionalUser = Optional.empty();
        System.out.println("apa");
        try {

            optionalUser = this.userRepository.findUserByUserCredentialUsernameAndUserCredentialPassword(loginRequest.getUsernameOrPassword(), loginRequest.getPassword());
            if (optionalUser.isEmpty()) {
                throw new UnautorizhedException("Username And Password don't match");
            }

        } catch (UnautorizhedException e) {
            throw new UnautorizhedException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        User user = optionalUser.get();
        System.out.println(user.getUserRoles().get(0).getRole().getName());
        String token = jwtUtil.generateToken(user.getUserRoles().get(0).getRole().getName(), user.getUserId());
        System.out.println(token);

        return token;
    }

    @Override
    public void logout(String token) {

    }



    @Override
    public User create(UserRequest userRequest) {
        return null;
    }

    @Override
    public User updateById(UserRequest userRequest, String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Page<User> findAllPagination(Pageable pageable) {
        return null;
    }

    @Override
    public Page<User> findByCategory(Pageable pageable, String category) {
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }
}
