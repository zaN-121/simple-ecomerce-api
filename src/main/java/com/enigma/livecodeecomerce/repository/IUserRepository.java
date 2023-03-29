package com.enigma.livecodeecomerce.repository;

import com.enigma.livecodeecomerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    public Optional<User> findUserByUserCredentialUsernameAndUserCredentialPassword(String username, String password);
}
