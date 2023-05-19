package com.appsdeveloper.app.ws.mobileappws.io.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
@Entity(name = "CommentEntity")
@Table(name = "comments")
@Getter
@Setter
public class CommentEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 3083236718203692879L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;


    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "trail_id", nullable = false)
    private TrailEntity trail;

}