package com.appsdeveloper.app.ws.mobileappws.service.implementation;

import com.appsdeveloper.app.ws.mobileappws.io.entity.FavouriteTrailsEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.TrailEntity;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.FavouriteTrailsRepository;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.TrailRepository;
import com.appsdeveloper.app.ws.mobileappws.service.FavouriteTrailsService;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.FavouriteTrailsDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.TrailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void addFavouriteTrail(long trailId, long userId) {
        FavouriteTrailsEntity existingEntity = favouriteTrailsRepository.findByUserIdAndTrailId(userId, trailId);
        if (existingEntity != null) {
            throw new RuntimeException("User already has that favorite Trail");
        }

        FavouriteTrailsEntity favouriteTrailsEntity = new FavouriteTrailsEntity();
        favouriteTrailsEntity.setTrailId(trailId);
        favouriteTrailsEntity.setUserId(userId);
        favouriteTrailsRepository.save(favouriteTrailsEntity);

    }

    @Override
    public void removeFavouriteTrail(long trailId, long userId) {
        FavouriteTrailsEntity favouriteTrailsEntity = favouriteTrailsRepository.findFavouriteTrailsEntityByTrailIdAndUserId(trailId,userId);
        favouriteTrailsRepository.delete(favouriteTrailsEntity);

    }
}