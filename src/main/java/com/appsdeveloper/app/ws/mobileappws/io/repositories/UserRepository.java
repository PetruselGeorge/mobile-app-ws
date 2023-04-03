package com.appsdeveloper.app.ws.mobileappws.io.repositories;

import com.appsdeveloper.app.ws.mobileappws.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
     UserEntity findUserEntityByEmail(String email);
     UserEntity findUserEntityByUserId(String userId);

}
