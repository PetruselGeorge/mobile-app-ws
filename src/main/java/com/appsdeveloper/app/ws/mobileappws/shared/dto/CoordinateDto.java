package com.appsdeveloper.app.ws.mobileappws.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class CoordinateDto implements Serializable {
    @Serial
    private final static long serialVersionUID = -4398645410333942904L;
    private long id;
    private double latitude;
    private double longitude;
    private TrailDto trailDto;

}
