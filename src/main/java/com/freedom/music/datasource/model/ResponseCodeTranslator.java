package com.freedom.music.datasource.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Table(name = "response_code_translator")
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class ResponseCodeTranslator {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String respCode;

    @Column
    private String respConstant;

}
