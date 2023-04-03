package com.appsdeveloper.app.ws.mobileappws.service.implementation;

import com.appsdeveloper.app.ws.mobileappws.io.repositories.UserRepository;
import com.appsdeveloper.app.ws.mobileappws.io.entity.UserEntity;
import com.appsdeveloper.app.ws.mobileappws.service.UserService;
import com.appsdeveloper.app.ws.mobileappws.shared.Utils;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public final UserRepository userRepository;
    public final Utils utils;
    public final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findUserEntityByEmail(userDto.getEmail())!=null)
            throw new RuntimeException("User already exists!");
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        userEntity.setUserId(utils.getUUID());
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        UserEntity storedUser = userRepository.save(userEntity);
        UserDto returnedValue = new UserDto();
        BeanUtils.copyProperties(storedUser, returnedValue);
        return returnedValue;
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if (userEntity==null) throw new UsernameNotFoundException(email);
        UserDto returnedValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnedValue);
        return returnedValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if (userEntity==null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public UserDto getUserByUserId(String id) {
        UserDto returnedValue=new UserDto();
        UserEntity userEntity=userRepository.findUserEntityByUserId(id);
        if (userEntity==null)throw new UsernameNotFoundException(id);
        BeanUtils.copyProperties(userEntity,returnedValue);
        return returnedValue;
    }
}
