package com.freedom.music.datasource.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Table(name = "music")
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class Music {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private String location;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_on", insertable = false, updatable = false)
    private Date createdOn;

    @Column(name = "updated_on", insertable = false, nullable = false)
    private Date updatedOn;

}