package com.enigma.livecodeecomerce.repository;

import com.enigma.livecodeecomerce.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, String> {
}
