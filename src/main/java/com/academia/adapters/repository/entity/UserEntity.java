package com.academia.adapters.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_USER")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String name;
    private Double weight;
    private Double height;
    private String sex;
    private String email;
    private String fone;

    @OneToOne
    private FileEntity fileEntity;
}
