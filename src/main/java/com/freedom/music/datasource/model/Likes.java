package com.freedom.music.datasource.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Table(name = "likes")
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class Likes {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long musicId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_on", insertable = false, updatable = false)
    private Date createdOn;

    @Column(name = "updated_on", insertable = false, nullable = false)
    private Date updatedOn;

}