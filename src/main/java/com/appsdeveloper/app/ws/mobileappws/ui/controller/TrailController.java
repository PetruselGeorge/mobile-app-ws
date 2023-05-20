package com.appsdeveloper.app.ws.mobileappws.ui.controller;

import com.appsdeveloper.app.ws.mobileappws.service.TrailService;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.CoordinateDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.TrailDto;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.CoordinateRest;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.TrailRest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("trails")
@RequiredArgsConstructor
public class TrailController {
    public final TrailService trailService;

    @GetMapping(path = "getTrails", produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<TrailRest> getTrails(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<TrailRest> returnedValue = new ArrayList<>();
        List<TrailDto> trailDtos = trailService.getTrails(page, limit);
        for (TrailDto trailDto : trailDtos) {
            TrailRest trailRest = new TrailRest();
            BeanUtils.copyProperties(trailDto, trailRest);

            List<CoordinateDto> coordinateDtoList = trailDto.getCoordinates();
            List<CoordinateRest> coordinateRestList = new ArrayList<>();
            if (coordinateDtoList != null) {
                for (CoordinateDto coordinateDto : coordinateDtoList) {
                    CoordinateRest coordinateRest = new CoordinateRest();
                    BeanUtils.copyProperties(coordinateDto, coordinateRest);
                    coordinateRestList.add(coordinateRest);
                }
            }
            trailRest.setCoordinates(coordinateRestList);

            returnedValue.add(trailRest);
        }
        return returnedValue;
    }



}