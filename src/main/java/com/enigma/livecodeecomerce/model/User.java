package com.enigma.livecodeecomerce.model;

import com.enigma.livecodeecomerce.util.constans.Gender;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Getter @Setter
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    @Column
    private String address;
    @Column
    private String phone;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "credential_id")
    private UserCredential userCredential;
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<UserRole> userRoles;
}
