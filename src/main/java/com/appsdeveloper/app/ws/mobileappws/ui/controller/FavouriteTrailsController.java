package com.appsdeveloper.app.ws.mobileappws.ui.controller;

import com.appsdeveloper.app.ws.mobileappws.service.FavouriteTrailsService;
import com.appsdeveloper.app.ws.mobileappws.service.UserService;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.FavouriteTrailsDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.TrailDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.UserDto;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.FavouriteTrailsRest;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.OperationStatusModel;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.TrailRest;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.enums.RequestOperationName;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.enums.RequestOperationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("favourite")
@RequiredArgsConstructor
public class FavouriteTrailsController {

    public final FavouriteTrailsService favouriteTrailsService;
    public final UserService userService;
    @GetMapping(path = "favouriteTrails", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TrailRest> getTrails(@RequestParam(value = "userId") String userId) {
        List<TrailRest> trailRests = new ArrayList<>();
        UserDto userDto= userService.getUserByUserId(userId);
        List<TrailDto> trailDtos = favouriteTrailsService.getTrailsByUser(userDto.getId());
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
    public OperationStatusModel addTrailToFavourite(@RequestParam(value = "trailId") long trailId,@RequestParam(value = "userId") String userId) {

        OperationStatusModel returnedValue = new OperationStatusModel();
        returnedValue.setOperationName(RequestOperationName.ADD_FAVOURITE_TRAIL.name());
        UserDto userDto= userService.getUserByUserId(userId);
        favouriteTrailsService.addFavouriteTrail(trailId, userDto.getId());
        returnedValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return returnedValue;
    }


    @DeleteMapping(path = "removeFavouriteTrail")
    public OperationStatusModel removeFavouriteTrail(@RequestParam(value = "trailId") long trailId,@RequestParam(value = "userId") String userId){
        OperationStatusModel returnedValue = new OperationStatusModel();
        returnedValue.setOperationName(RequestOperationName.REMOVE_FAVOURITE_TRAIL.name());
        UserDto userDto= userService.getUserByUserId(userId);
        favouriteTrailsService.removeFavouriteTrail(trailId, userDto.getId());
        returnedValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnedValue;

    }

}