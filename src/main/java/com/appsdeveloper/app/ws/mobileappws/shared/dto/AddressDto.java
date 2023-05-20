package com.appsdeveloper.app.ws.mobileappws.shared.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressDto {
    private long id;
    private String addressId;
    private String city;
    private String country;
    private String streetName;
    private String postalCode;
    private UserDto userDto;
}
