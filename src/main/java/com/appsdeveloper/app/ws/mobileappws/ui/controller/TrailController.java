package com.appsdeveloper.app.ws.mobileappws.ui.controller;

import com.appsdeveloper.app.ws.mobileappws.service.TrailService;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.CommentDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.CoordinateDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.ImagesDto;
import com.appsdeveloper.app.ws.mobileappws.shared.dto.TrailDto;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.CommentRest;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.CoordinateRest;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.ImageRest;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.TrailRest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("trails")
@RequiredArgsConstructor
public class TrailController {
    public final TrailService trailService;

    @GetMapping(path = "getTrails", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TrailRest> getTrails() {
        List<TrailRest> returnedValue = new ArrayList<>();
        List<TrailDto> trailDtos = trailService.getTrails();
        for (TrailDto trailDto : trailDtos) {
            TrailRest trailRest = new TrailRest();
            BeanUtils.copyProperties(trailDto, trailRest);

            List<CoordinateDto> coordinateDtoList = trailDto.getCoordinates();
            List<CoordinateRest> coordinateRestList = new ArrayList<>();
            if (coordinateDtoList != null) {
                for (CoordinateDto coordinateDto : coordinateDtoList) {
                    CoordinateRest coordinateRest = new CoordinateRest();
                    BeanUtils.copyProperties(coordinateDto, coordinateRest);
                    coordinateRestList.add(coordinateRest);
                }
            }
            trailRest.setCoordinates(coordinateRestList);

            List<CommentDto> commentDtos = trailDto.getComments();
            List<CommentRest> commentRests = new ArrayList<>();
            if (commentDtos != null) {
                for (CommentDto commentDto : commentDtos) {
                    CommentRest commentRest = new CommentRest();
                    BeanUtils.copyProperties(commentDto, commentRest);
                    commentRests.add(commentRest);
                }
            }
            trailRest.setComments(commentRests);

            List<ImagesDto> imagesDtos = trailDto.getImages();
            List<ImageRest> imageRests = new ArrayList<>();
            if (imagesDtos != null) {
                for (ImagesDto imagesDto : imagesDtos) {
                    ImageRest imageRest = new ImageRest();
                    BeanUtils.copyProperties(imagesDto, imageRest);
                    imageRests.add(imageRest);
                }
            }
            trailRest.setImages(imageRests);

            returnedValue.add(trailRest);
        }
        return returnedValue;
    }

    @PostMapping(path = "addComment", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public CommentRest addComment(@RequestParam(value = "trailId") long trailId,
                                  @RequestBody CommentRest commentRest) {
        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(commentRest, commentDto);


        CommentDto createdComment = trailService.addCommentForATrail(trailId, commentDto);

        CommentRest createdCommentRest = new CommentRest();
        BeanUtils.copyProperties(createdComment, createdCommentRest);

        return createdCommentRest;
    }


    @GetMapping(path = "comments", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CommentRest> getComments(@RequestParam(value = "trailId") long trailId) {
        List<CommentRest> commentRests = new ArrayList<>();

        List<CommentDto> commentDtos = trailService.getAllCommentsForATrail(trailId);
        if (commentDtos != null) {
            for (CommentDto commentDto : commentDtos) {
                CommentRest commentRest = new CommentRest();
                BeanUtils.copyProperties(commentDto, commentRest);
                commentRests.add(commentRest);
            }
        }

        return commentRests;
    }
}
