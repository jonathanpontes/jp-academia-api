package com.academia.adapters.dtos;

import lombok.Data;


@Data
public class UserDto {

    private String name;
    private Double weight;
    private Double height;
    private String sex;
    private String email;
    private String fone;
    private String imageDownloadURL;
}
