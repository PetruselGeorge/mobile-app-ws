package com.appsdeveloper.app.ws.mobileappws.service.implementation;

import com.appsdeveloper.app.ws.mobileappws.io.entity.CommentEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.FavouriteTrailsEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.TrailEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.UserEntity;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.FavouriteTrailsRepository;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.TrailRepository;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.UserRepository;
import com.appsdeveloper.app.ws.mobileappws.service.FavouriteTrailsService;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.CommentDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.TrailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavouriteTrailsServiceImpl implements FavouriteTrailsService {

    private final FavouriteTrailsRepository favouriteTrailsRepository;
    private final TrailRepository trailRepository;

    @Override
    public List<TrailDto> getTrailsByUser(long userId) {
        List<TrailDto> trailDtos = new ArrayList<>();

        List<FavouriteTrailsEntity> favouriteTrailsEntities = favouriteTrailsRepository.findByUserId(userId);

        for (FavouriteTrailsEntity favouriteTrailsEntity : favouriteTrailsEntities) {
            TrailEntity trailEntity = trailRepository.findTrailEntityById(favouriteTrailsEntity.getTrailId());
            TrailDto trailDto = new TrailDto();
            BeanUtils.copyProperties(trailEntity, trailDto);
            trailDtos.add(trailDto);
        }


        return trailDtos;
    }
}