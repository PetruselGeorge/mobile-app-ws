package com.appsdeveloper.app.ws.mobileappws.shared.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CoordinateDto {
    private long id;
    private double latitude;
    private double longitude;
    private TrailDto trailDto;

}
