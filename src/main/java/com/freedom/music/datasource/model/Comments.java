package com.freedom.music.datasource.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Table(name = "comments")
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class Comments {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String data;

    @Column
    private String parentId;

    @Column
    private String musicId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_on", insertable = false, updatable = false)
    private Date createdOn;

    @Column(name = "updated_on", insertable = false, nullable = false)
    private Date updatedOn;

}