package com.enigma.livecodeecomerce.model.request;

import com.enigma.livecodeecomerce.util.constans.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserRequest {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Gender gender;
    private String address;
    private String phone;
    private String username;
    private String email;
    private String password;
}
