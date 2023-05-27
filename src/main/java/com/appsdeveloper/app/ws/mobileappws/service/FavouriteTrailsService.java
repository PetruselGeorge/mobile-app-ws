package com.appsdeveloper.app.ws.mobileappws.service;

import com.appsdeveloper.app.ws.mobileappws.shared.dto.FavouriteTrailsDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.TrailDto;

import java.util.List;

public interface FavouriteTrailsService {
    List<TrailDto> getTrailsByUser(long user_id);

    void addFavouriteTrail (long trailId, long userId);

    void removeFavouriteTrail (long trailId, long userId);
}
