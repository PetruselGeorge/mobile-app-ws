package com.appsdeveloper.app.ws.mobileappws.io.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "UserEntity")
@Table(name = "users")
@Getter
@Setter
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 54327432878412803L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;
    @Column(name = "encrypted_password", nullable = false)
    private String encryptedPassword;
    @Column(name = "email_verification_token")
    private String emailVerificationToken;
    @Column(name = "email_verification_status")
    private Boolean emailVerificationStatus = false;
    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private AddressEntity addressEntity;
}
