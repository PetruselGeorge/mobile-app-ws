package com.appsdeveloper.app.ws.mobileappws.ui.model.response;


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
    private double length;
    private String time;
    private List<CommentRest> comments;
    private List<CoordinateRest> coordinates;
    private List<ImageRest> images;
}
