package com.appsdeveloper.app.ws.mobileappws.io.repositories;

import com.appsdeveloper.app.ws.mobileappws.io.entity.FavouriteTrailsEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.TrailEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavouriteTrailsRepository  extends CrudRepository<FavouriteTrailsEntity,Long> {
    List<FavouriteTrailsEntity> findByUserId(long user_id);

    FavouriteTrailsEntity findByUserIdAndTrailId(long userId, long trailId);

}
