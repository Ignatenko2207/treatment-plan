package com.aiomed.treatmentplan.model;

import com.aiomed.treatmentplan.model.enums.Gender;
import com.aiomed.treatmentplan.model.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

//    Properties for authorization
//    @Column(name = "login", nullable = false)
//    private String login;
//
//    @Column(name = "password", nullable = false)
//    private String password;

//    role can be taken from auth token (in the next release)
//    Security config is required
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole userRole;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

}
