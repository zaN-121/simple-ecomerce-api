package com.enigma.livecodeecomerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "tb_role")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String roleId;
    @Column
    private String name;
    @OneToMany(mappedBy = "role")
    @JsonManagedReference
    private List<UserRole> userRoles;
}
