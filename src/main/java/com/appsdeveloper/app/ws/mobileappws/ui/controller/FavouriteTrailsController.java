package com.appsdeveloper.app.ws.mobileappws.ui.controller;

import com.appsdeveloper.app.ws.mobileappws.service.FavouriteTrailsService;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.CommentDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.TrailDto;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.CommentRest;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.TrailRest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
