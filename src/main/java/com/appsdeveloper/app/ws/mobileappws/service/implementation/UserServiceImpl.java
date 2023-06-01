package com.appsdeveloper.app.ws.mobileappws.service.implementation;

import com.appsdeveloper.app.ws.mobileappws.io.entity.AddressEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.FavouriteTrailsEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.PasswordResetTokenEntity;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.AddressRepository;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.FavouriteTrailsRepository;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.PasswordResetTokenRepository;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.UserRepository;
import com.appsdeveloper.app.ws.mobileappws.io.entity.UserEntity;
import com.appsdeveloper.app.ws.mobileappws.service.UserService;
import com.appsdeveloper.app.ws.mobileappws.shared.AmazonSES;
import com.appsdeveloper.app.ws.mobileappws.shared.Utils;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.AddressDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public final UserRepository userRepository;
    public final AddressRepository addressRepository;
    public final FavouriteTrailsRepository favouriteTrailsRepository;

    public final Utils utils;
    public final BCryptPasswordEncoder bCryptPasswordEncoder;
    public final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.findUserEntityByEmail(userDto.getEmail()) != null)
            throw new RuntimeException("User already exists!");
        AddressDto addressDto = userDto.getAddressDto();
        addressDto.setUserDto(userDto);
        addressDto.setAddressId(utils.getUUID());

        userDto.setAddressDto(addressDto);
        ModelMapper modelMapper = new ModelMapper();
        AddressEntity addressEntity = modelMapper.map(addressDto, AddressEntity.class);
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        String publicUserID = utils.getUUID();
        userEntity.setUserId(publicUserID);
        addressDto.getUserDto().setUserId(userEntity.getUserId());
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        userEntity.setEmailVerificationToken(Utils.generateEmailVerificationToken(publicUserID));

        addressEntity.setUserEntity(userEntity);
        UserDto returnedValue = modelMapper.map(userEntity, UserDto.class);
        userEntity.setEmailVerificationStatus(true);
        userEntity.setEmailVerificationToken(null);
        userRepository.save(userEntity);
        addressRepository.save(addressEntity);

        return returnedValue;
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        UserDto returnedValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnedValue);
        return returnedValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), userEntity.getEmailVerificationStatus(), true, true, true, new ArrayList<>());
    }

    @Override
    public UserDto getUserByUserId(String id) {
        UserDto returnedValue = new UserDto();
        UserEntity userEntity = userRepository.findUserEntityByUserId(id);
        if (userEntity == null) throw new UsernameNotFoundException(id);
        BeanUtils.copyProperties(userEntity, returnedValue);
        return returnedValue;
    }

    @Override
    public UserDto updateUser(String id, UserDto dto) {
        UserDto returnedValue = new UserDto();
        UserEntity userEntity = userRepository.findUserEntityByUserId(id);
        if (userEntity == null) throw new UsernameNotFoundException(id);
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        UserEntity storedUser = userRepository.save(userEntity);
        BeanUtils.copyProperties(storedUser, returnedValue);
        return returnedValue;
    }

    @Override
    public void deleteUser(String id) {
        UserEntity userEntity = userRepository.findUserEntityByUserId(id);
        List<FavouriteTrailsEntity>favouriteTrailsEntities=favouriteTrailsRepository.findByUserId(userEntity.getId());
        favouriteTrailsRepository.deleteAll(favouriteTrailsEntities);
        if (userEntity == null) throw new UsernameNotFoundException(id);
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnedValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<UserEntity> userEntityPage = userRepository.findAll(pageableRequest);
        List<UserEntity> users = userEntityPage.getContent();
        for (UserEntity userEntity : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            returnedValue.add(userDto);
        }
        return returnedValue;
    }

    @Override
    public boolean verifyEmailToken(String token) {
        boolean returnedValue = false;
        UserEntity userEntity = userRepository.findUserEntityByEmailVerificationToken(token);
        if (userEntity != null) {
            boolean hasTokenExpired = Utils.hasTokenExpired(token);
            if (!hasTokenExpired) {
                userEntity.setEmailVerificationToken(null);
                userEntity.setEmailVerificationStatus(Boolean.TRUE);
                userRepository.save(userEntity);
                returnedValue = true;
            }
        }
        return returnedValue;
    }

    @Override
    public boolean requestPasswordReset(String email) {
        boolean returnedValue = false;
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if (userEntity == null) {
            return returnedValue;
        }
        String token = Utils.generatePasswordResetToken(userEntity.getUserId());
        PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
        passwordResetTokenEntity.setToken(token);
        passwordResetTokenEntity.setUserEntity(userEntity);
        passwordResetTokenRepository.save(passwordResetTokenEntity);
        return new AmazonSES().sendPasswordResetRequest(
                userEntity.getFirstName(),
                userEntity.getEmail(),
                token
        );
    }
}