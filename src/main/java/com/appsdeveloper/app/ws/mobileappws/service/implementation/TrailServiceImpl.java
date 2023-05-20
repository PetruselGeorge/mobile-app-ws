package com.appsdeveloper.app.ws.mobileappws.service.implementation;

import com.appsdeveloper.app.ws.mobileappws.io.entity.CommentEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.CoordinateEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.ImagesEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.TrailEntity;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.CommentRepository;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.CoordinateRepository;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.ImagesRepository;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.TrailRepository;
import com.appsdeveloper.app.ws.mobileappws.service.TrailService;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrailServiceImpl implements TrailService {
    public final TrailRepository trailRepository;
    public final ImagesRepository imagesRepository;
    public final CoordinateRepository coordinateRepository;
    public final CommentRepository commentRepository;


    @Override
    public List<TrailDto> getTrails(int page, int limit) {
        List<TrailDto> returnedValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<TrailEntity> trailEntities = trailRepository.findAll(pageableRequest);
        List<TrailEntity> trails = trailEntities.getContent();
        for (TrailEntity trailEntity : trails) {
            TrailDto trailDto = new TrailDto();
            BeanUtils.copyProperties(trailEntity, trailDto);

            List<CoordinateDto> coordinateDtos = new ArrayList<>();
            List<CoordinateEntity> coordinateEntities = coordinateRepository.findAllByTrail(trailEntity);
            for (CoordinateEntity coordinateEntity : coordinateEntities) {
                CoordinateDto coordinateDto = new CoordinateDto();
                BeanUtils.copyProperties(coordinateEntity, coordinateDto);
                coordinateDtos.add(coordinateDto);
            }
            trailDto.setCoordinates(coordinateDtos);

            returnedValue.add(trailDto);
        }
        return returnedValue;
    }

    @Override
    public List<CommentDto> getAllCommentsForATrail(long id, int page, int limit) {
        List<CommentDto> returnedValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        TrailEntity trailEntity = trailRepository.findTrailEntityById(id);
        Page<CommentEntity> commentEntityPage = commentRepository.findAllByTrail(trailEntity, pageableRequest);
        List<CommentEntity> commentEntities = commentEntityPage.getContent();
        for (CommentEntity commentEntity : commentEntities) {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(commentEntity, commentDto);
            returnedValue.add(commentDto);
        }
        return returnedValue;
    }

    @Override
    public List<ImagesDto> getAllImagesForATrail(long id) {
        List<ImagesDto> returnedValue = new ArrayList<>();
        TrailEntity trailEntity = trailRepository.findTrailEntityById(id);
        List<ImagesEntity> imagesEntities = imagesRepository.findAllByTrail(trailEntity);
        for (ImagesEntity imagesEntity : imagesEntities) {
            ImagesDto imagesDto = new ImagesDto();
            BeanUtils.copyProperties(imagesEntity, imagesDto);
            returnedValue.add(imagesDto);
        }
        return returnedValue;
    }

    @Override
    public List<CoordinateDto> getAllCoordinatesForATrail(long id) {
        List<CoordinateDto> returnedValue = new ArrayList<>();
        TrailEntity trailEntity = trailRepository.findTrailEntityById(id);
        List<CoordinateEntity> coordinateEntities = coordinateRepository.findAllByTrail(trailEntity);
        for (CoordinateEntity coordinateEntity : coordinateEntities) {
            CoordinateDto coordinateDto = new CoordinateDto();
            BeanUtils.copyProperties(coordinateEntity, coordinateDto);
            returnedValue.add(coordinateDto);
        }
        return returnedValue;    }
}
