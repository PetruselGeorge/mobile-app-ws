package com.appsdeveloper.app.ws.mobileappws.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class CommentDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 2773385043045881722L;
    private long id;
    private String comment;
    private TrailDto trailDto;

}
