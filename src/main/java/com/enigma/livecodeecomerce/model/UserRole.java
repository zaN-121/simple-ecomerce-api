package com.enigma.livecodeecomerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Table(name = "tb_user_role")
@NoArgsConstructor
@Getter @Setter
@Accessors(chain = true)
@ToString
public class UserRole {
    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userRoleId;
    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private User user;
    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Role role;
}
