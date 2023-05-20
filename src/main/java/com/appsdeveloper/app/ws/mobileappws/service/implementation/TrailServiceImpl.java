package com.appsdeveloper.app.ws.mobileappws.service.implementation;

import com.appsdeveloper.app.ws.mobileappws.io.repositories.CommentRepository;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.CoordinateRepository;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.ImagesRepository;
import com.appsdeveloper.app.ws.mobileappws.io.repositories.TrailRepository;
import com.appsdeveloper.app.ws.mobileappws.service.TrailService;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.CommentDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.CoordinateDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.ImagesDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.TrailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrailServiceImpl implements TrailService {
    public final TrailRepository trailRepository;
    public final ImagesRepository imagesRepository;
    public final CoordinateRepository coordinateRepository;
    public final CommentRepository commentRepository;


    @Override
    public List<TrailDto> getTrails(int page, int limit) {
        return null;
    }

    @Override
    public List<CommentDto> getAllCommentsForATrail(long id) {
        return null;
    }

    @Override
    public List<ImagesDto> getAllImagesForATrail(long id) {
        return null;
    }

    @Override
    public List<CoordinateDto> getAllCoordinatesForATrail(long id) {
        return null;
    }
}
