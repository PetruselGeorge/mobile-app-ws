package com.appsdeveloper.app.ws.mobileappws.io.repositories;

import com.appsdeveloper.app.ws.mobileappws.io.entity.CommentEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.TrailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends PagingAndSortingRepository<CommentEntity,Long> {
    Page<CommentEntity> findAllByTrail(TrailEntity trail, Pageable pageableRequest);
}
