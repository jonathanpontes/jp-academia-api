package com.academia.application.domain.models;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileDomain {

    private String id;
    private String name;
    private String contentType;
    private Long size;
    private byte[] data;

    public FileDomain(MultipartFile file) {

        this.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        this.setContentType(file.getContentType());

        try {
            this.setData(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setSize(file.getSize());
    }

    public FileDomain() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
