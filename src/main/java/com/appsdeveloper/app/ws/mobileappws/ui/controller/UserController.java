package com.appsdeveloper.app.ws.mobileappws.ui.controller;

import com.appsdeveloper.app.ws.mobileappws.service.UserService;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.UserDto;
import com.appsdeveloper.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.UserRest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id) {
        UserRest returnedValue = new UserRest();
        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnedValue);
        return returnedValue;
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {
        UserRest returnedValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetailsRequestModel, userDto);
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnedValue);
        return returnedValue;
    }

    @PostMapping("login")
    public String userLogin() {
        return "logg in successful";
    }

    @PutMapping
    public String updateUser() {
        return "put mapping was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete was called";
    }

}
