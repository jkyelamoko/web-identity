package com.devhome.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "passportdetail")
@Data
public class PassportDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "passportno")
    private String passportNo;
}
