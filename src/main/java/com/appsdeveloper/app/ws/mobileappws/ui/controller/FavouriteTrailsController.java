package com.appsdeveloper.app.ws.mobileappws.ui.controller;

import com.appsdeveloper.app.ws.mobileappws.service.FavouriteTrailsService;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.FavouriteTrailsDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.TrailDto;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.FavouriteTrailsRest;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.OperationStatusModel;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.TrailRest;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.enums.RequestOperationName;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.enums.RequestOperationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("favourite")
@RequiredArgsConstructor
public class FavouriteTrailsController {

    public final FavouriteTrailsService favouriteTrailsService;

    @GetMapping(path = "favouriteTrails", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TrailRest> getTrails(@RequestParam(value = "userId") long userId) {
        List<TrailRest> trailRests = new ArrayList<>();

        List<TrailDto> trailDtos = favouriteTrailsService.getTrailsByUser(userId);
        if (trailDtos != null) {
            for (TrailDto trailDto : trailDtos) {
                TrailRest trailRest = new TrailRest();
                BeanUtils.copyProperties(trailDto, trailRest);
                trailRests.add(trailRest);
            }
        }

        return trailRests;
    }


    @PostMapping(path = "addFavouriteTrail")
    public OperationStatusModel addTrailToFavourite(@RequestParam(value = "userId") long userId, @RequestParam(value = "trailId") long trailId) {

        OperationStatusModel returnedValue = new OperationStatusModel();
        returnedValue.setOperationName(RequestOperationName.ADD_FAVOURITE_TRAIL.name());
        FavouriteTrailsDto favouriteTrailsDto = favouriteTrailsService.addFavouriteTrail(trailId, userId);
        returnedValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        FavouriteTrailsRest favouriteTrailsRest = new FavouriteTrailsRest();
        BeanUtils.copyProperties(favouriteTrailsDto, favouriteTrailsRest);

        return returnedValue;
    }
}