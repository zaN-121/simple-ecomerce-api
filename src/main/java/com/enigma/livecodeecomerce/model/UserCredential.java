package com.enigma.livecodeecomerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "tb_user_credential")
@NoArgsConstructor
@Getter @Setter
@Accessors(chain = true)
public class UserCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String credentialId;
    @Column
    private String email;
    @Column
    private String username;
    @Column
    private String password;
}
