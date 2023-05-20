package com.appsdeveloper.app.ws.mobileappws.io.repositories;

import com.appsdeveloper.app.ws.mobileappws.io.entity.CoordinateEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.TrailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordinateRepository extends CrudRepository<CoordinateEntity, Long> {

    List<CoordinateEntity> findAllByTrail(TrailEntity trailEntity);
}
