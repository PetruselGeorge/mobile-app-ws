package com.appsdeveloper.app.ws.mobileappws.io.repositories;

import com.appsdeveloper.app.ws.mobileappws.io.entity.CommentEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.TrailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends CrudRepository<CommentEntity,Long> {
    List<CommentEntity> findAllByTrail(TrailEntity trail);
}
