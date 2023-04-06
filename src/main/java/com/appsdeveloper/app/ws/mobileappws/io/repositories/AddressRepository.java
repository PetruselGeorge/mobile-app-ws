package com.appsdeveloper.app.ws.mobileappws.io.repositories;

import com.appsdeveloper.app.ws.mobileappws.io.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}
