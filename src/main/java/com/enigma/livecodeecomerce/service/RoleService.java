package com.enigma.livecodeecomerce.service;

import com.enigma.livecodeecomerce.exception.NotFoundException;
import com.enigma.livecodeecomerce.model.Role;
import com.enigma.livecodeecomerce.model.request.RoleRequest;
import com.enigma.livecodeecomerce.repository.IRoleRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
@Getter @Setter
@NoArgsConstructor
public class RoleService implements IService<Role, RoleRequest> {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public Role create(RoleRequest roleRequest) {
        Role role = modelMapper.map(roleRequest, Role.class);

        try {
            role = this.roleRepository.save(role);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return role;
    }

    @Override
    public Role updateById(RoleRequest roleRequest, String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Page<Role> findAllPagination(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Role> findByCategory(Pageable pageable, String category) {
        return null;
    }

    @Override
    public Optional<Role> findById(String id) {
        Optional<Role> optionalRole = Optional.empty();

        try {
            optionalRole = this.roleRepository.findById(id);
            if (optionalRole.isEmpty()) {
                throw new NotFoundException("role not found");
            }

        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return optionalRole;
    }

    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }
}
