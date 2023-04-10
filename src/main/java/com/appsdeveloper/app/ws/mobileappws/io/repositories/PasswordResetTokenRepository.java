package com.appsdeveloper.app.ws.mobileappws.io.repositories;

import com.appsdeveloper.app.ws.mobileappws.io.entity.PasswordResetTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetTokenEntity, Long> {
}
