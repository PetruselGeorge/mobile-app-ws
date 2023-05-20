package com.appsdeveloper.app.ws.mobileappws.io.repositories;

import com.appsdeveloper.app.ws.mobileappws.io.entity.ImagesEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.TrailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends CrudRepository<ImagesEntity, Long> {

    List<ImagesEntity> findAllByTrail(TrailEntity trailEntity);
}
