package com.enigma.livecodeecomerce.repository;

import com.enigma.livecodeecomerce.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String name);
}
