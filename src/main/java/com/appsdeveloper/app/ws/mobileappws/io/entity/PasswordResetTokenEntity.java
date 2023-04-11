package com.appsdeveloper.app.ws.mobileappws.io.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Entity(name = "PasswordResetTokenEntity")
@Table(name = "reset_token_passwords")
public class PasswordResetTokenEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1808744296809905410L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "token")
    private String token;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
