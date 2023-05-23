package com.appsdeveloper.app.ws.mobileappws.service.implementation;

import com.appsdeveloper.app.ws.mobileappws.io.entity.*;
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
            trailDto.setLength(calculateTrailLength(coordinateDtos));
            TrailEntity saved=new TrailEntity();
            BeanUtils.copyProperties(trailDto,saved);
            trailRepository.save(saved);


            List<CommentDto> commentDtos = new ArrayList<>();
            List<CommentEntity> commentEntities = commentRepository.findAllByTrail(trailEntity);
            for (CommentEntity commentEntity : commentEntities) {
                CommentDto commentDto = new CommentDto();
                BeanUtils.copyProperties(commentEntity, commentDto);
                commentDtos.add(commentDto);
            }
            trailDto.setComments(commentDtos);

            List<ImagesDto> imagesDtos = new ArrayList<>();
            List<ImagesEntity> imagesEntities = imagesRepository.findAllByTrail(trailEntity);
            for (ImagesEntity imagesEntity : imagesEntities) {
                ImagesDto imagesDto = new ImagesDto();
                BeanUtils.copyProperties(imagesEntity, imagesDto);
                imagesDtos.add(imagesDto);
            }
            trailDto.setImages(imagesDtos);


            returnedValue.add(trailDto);
        }
        return returnedValue;
    }

    @Override
    public List<CommentDto> getAllCommentsForATrail(long id) {
        List<CommentDto> returnedValue = new ArrayList<>();
        TrailEntity trailEntity = trailRepository.findTrailEntityById(id);
        List<CommentEntity> commentEntities = commentRepository.findAllByTrail(trailEntity);
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
        return returnedValue;
    }


    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

    @Override
    public double calculateTrailLength(List<CoordinateDto> coordinates) {
        double trailLength = 0;

        for (int i = 0; i < coordinates.size() - 1; i++) {
            CoordinateDto currentCoordinate = coordinates.get(i);
            CoordinateDto nextCoordinate = coordinates.get(i + 1);

            double distance = calculateDistance(
                    currentCoordinate.getLatitude(),
                    currentCoordinate.getLongitude(),
                    nextCoordinate.getLatitude(),
                    nextCoordinate.getLongitude()
            );

            trailLength += distance;
        }

        return trailLength;
    }
}
