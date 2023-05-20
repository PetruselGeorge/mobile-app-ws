package com.appsdeveloper.app.ws.mobileappws.shared.dto;

import com.appsdeveloper.app.ws.mobileappws.io.entity.CommentEntity;
import com.appsdeveloper.app.ws.mobileappws.io.entity.CoordinateEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class TrailDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -3436065095156731477L;
    private long id;
    private String name;
    private String difficulty;
    private byte[] mainImage;
    private List<CommentDto> comments;
    private List<CoordinateDto> coordinates;
    private List<ImagesDto> images;

}
