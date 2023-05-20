package com.appsdeveloper.app.ws.mobileappws.ui.model.response;

import com.appsdeveloper.app.ws.mobileappws.shared.dto.CommentDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.ImagesDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrailRest {
    private long id;
    private String name;
    private String difficulty;
    private byte[] mainImage;
    private List<CommentRest> comments;
    private List<CoordinateRest> coordinates;
    private List<ImageRest> images;
}
