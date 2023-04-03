package com.appsdeveloper.app.ws.mobileappws.service;

import com.appsdeveloper.app.ws.mobileappws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto getUser(String email);

    UserDto getUserByUserId(String id);
}
