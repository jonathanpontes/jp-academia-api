package com.academia.adapters.dtos;

import lombok.Data;

@Data
public class FileDto {

    private String id;
    private String name;
    private Long size;
    private String url;
    private String contentType;
}

