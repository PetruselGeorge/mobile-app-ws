package com.appsdeveloper.app.ws.mobileappws.io.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "trail_images")
public class ImageEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2973835400417954875L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "image_id", length = 30, nullable = false)
    private String imageId;

    @Lob
    @Column(name = "image", nullable = false)
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "trail_id", nullable = false)
    private TrailEntity trail;
}

