package com.appsdeveloper.app.ws.mobileappws.ui.controller;

import com.appsdeveloper.app.ws.mobileappws.exception.UserServiceException;
import com.appsdeveloper.app.ws.mobileappws.service.UserService;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.AddressDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.UserDto;
import com.appsdeveloper.app.ws.mobileappws.ui.model.request.PasswordResetRequestModel;
import com.appsdeveloper.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.OperationStatusModel;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.enums.RequestOperationName;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.UserRest;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.enums.RequestOperationStatus;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.errors.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
    public UserRest getUser(@PathVariable String id) {
        UserRest returnedValue = new UserRest();
        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnedValue);
        return returnedValue;
    }

    @PostMapping( path = "/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {
        if (userDetailsRequestModel.getFirstName().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetailsRequestModel, UserDto.class);
        AddressDto addressDto = modelMapper.map(userDetailsRequestModel.getAddress(), AddressDto.class);
        userDto.setAddressDto(addressDto);
        UserDto createdUser = userService.createUser(userDto);
        return modelMapper.map(createdUser, UserRest.class);
    }

    @PutMapping(path = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel requestModel) {
        UserRest returnedValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(requestModel, userDto);
        UserDto updatedUser = userService.updateUser(id, userDto);
        BeanUtils.copyProperties(updatedUser, returnedValue);
        return returnedValue;
    }

    @DeleteMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel returnedValue = new OperationStatusModel();
        returnedValue.setOperationName(RequestOperationName.DELETE.name());
        userService.deleteUser(id);
        returnedValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnedValue;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<UserRest> returnedValue = new ArrayList<>();
        List<UserDto> userDos = userService.getUsers(page, limit);
        for (UserDto userDto : userDos) {
            UserRest userRest = new UserRest();
            BeanUtils.copyProperties(userDto, userRest);
            returnedValue.add(userRest);
        }
        return returnedValue;


    }

    @GetMapping(path = "/email-verification",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public OperationStatusModel verifyEmailToken(@RequestParam(value = "token") String token) {
        OperationStatusModel returnedValue = new OperationStatusModel();
        returnedValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());
        boolean isVerified = userService.verifyEmailToken(token);
        if (isVerified) {
            returnedValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        } else {
            returnedValue.setOperationResult(RequestOperationStatus.ERROR.name());
        }
        return returnedValue;
    }

    @PostMapping(path = "/password-reset-request",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public OperationStatusModel requestReset(@RequestBody PasswordResetRequestModel passwordResetRequestModel) {
        OperationStatusModel returnedValue = new OperationStatusModel();
        boolean operationResult = userService.requestPasswordReset(passwordResetRequestModel.getEmail());
        returnedValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
        returnedValue.setOperationResult(RequestOperationStatus.ERROR.name());
        if (operationResult) {
            returnedValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }
        return returnedValue;
    }


}
