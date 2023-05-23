package com.appsdeveloper.app.ws.mobileappws.service;

import com.appsdeveloper.app.ws.mobileappws.shared.dto.CommentDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.CoordinateDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.ImagesDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.TrailDto;

import java.util.List;

public interface TrailService {

    List<TrailDto> getTrails(int page, int limit);

    double calculateTrailLength(List<CoordinateDto> coordinates);

    List<CommentDto> getAllCommentsForATrail(long id);

    List<ImagesDto> getAllImagesForATrail(long id);

    List<CoordinateDto> getAllCoordinatesForATrail(long id);
}
