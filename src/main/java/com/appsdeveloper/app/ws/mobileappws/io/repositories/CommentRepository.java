package com.appsdeveloper.app.ws.mobileappws.io.repositories;

import com.appsdeveloper.app.ws.mobileappws.io.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity,Long> {

}
