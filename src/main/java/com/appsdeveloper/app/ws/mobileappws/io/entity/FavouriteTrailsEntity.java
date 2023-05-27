package com.appsdeveloper.app.ws.mobileappws.io.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "FavouriteTrailsEntity")
@Table(name = "favourite_trails")
@Getter
@Setter
public class FavouriteTrailsEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1116678801884672062L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "trail_id")
    private long trailId;

    @Column(name = "user_id")
    private long userId;

}
