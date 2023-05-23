package com.appsdeveloper.app.ws.mobileappws.io.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity(name = "TrailEntity")
@Table(name = "trails")
public class TrailEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -8689824866406496066L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "difficulty", nullable = false)
    private String difficulty;


    @Column(name = "length", nullable = false)
    private double length;

    @Column(name = "time", nullable = false)
    private String time;


    @Lob
    @Column(name = "main_image", nullable = false)
    private byte[] mainImage;

    @OneToMany(mappedBy = "trail", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "trail", cascade = CascadeType.ALL)
    private List<CoordinateEntity> coordinates;

    @OneToMany(mappedBy = "trail", cascade = CascadeType.ALL)
    private List<CoordinateEntity> images;

}
