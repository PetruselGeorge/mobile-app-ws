package com.appsdeveloper.app.ws.mobileappws.ui.controller;

import com.appsdeveloper.app.ws.mobileappws.service.TrailService;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.TrailDto;
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
@RequestMapping("trails")
@RequiredArgsConstructor
public class TrailController {
    public final TrailService trailService;

    @GetMapping(path = "getTrails",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TrailRest> getTrails(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "25") int limit) {
        List<TrailRest> returnedValue = new ArrayList<>();
        List<TrailDto> trailDtos = trailService.getTrails(page, limit);
        for (TrailDto trailDto : trailDtos) {
            TrailRest trailRest = new TrailRest();
            BeanUtils.copyProperties(trailDto, trailRest);
            returnedValue.add(trailRest);
        }
        return returnedValue;


    }


}
