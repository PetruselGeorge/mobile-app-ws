package com.appsdeveloper.app.ws.mobileappws.shared.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 943783249363465L;
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;

    private String emailVerificationToken;
    private Boolean emailVerificationStatus = false;
    private AddressDto addressDto;

}
