package com.academia.adapters.dtos;

import lombok.Data;

import java.util.UUID;


@Data
public class UserDto {

    private UUID id;
    private String name;
    private Double weight;
    private Double height;
    private String sex;
    private String email;
    private String fone;
    private String imageDownloadURL;
}
