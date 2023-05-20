package com.appsdeveloper.app.ws.mobileappws.shared.dto;

import com.appsdeveloper.app.ws.mobileappws.io.entity.TrailEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class ImagesDto implements Serializable {
    @Serial
    private static final long serialVersionUID= 4405597646730721309L;

    private long id;
    private byte[] image;
    private TrailDto trailDto;

}
