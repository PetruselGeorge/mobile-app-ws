package com.appsdeveloper.app.ws.mobileappws.io.repositories;

import com.appsdeveloper.app.ws.mobileappws.io.entity.TrailEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrailRepository extends CrudRepository<TrailEntity, Long> {
    TrailEntity findTrailEntityById(long id);
}
