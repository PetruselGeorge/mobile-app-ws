package com.appsdeveloper.app.ws.mobileappws.io.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
@Entity(name = "CoordinateEntity")
@Table(name = "coordinates")
@Getter
@Setter
public class CoordinateEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 82721689865519303L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "trail_id", nullable = false)
    private TrailEntity trail;

}
