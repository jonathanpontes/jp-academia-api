package com.academia.adapters.utils;

import com.academia.application.domain.models.FileDomain;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class FileDomainHandler {

    public static String getDownloadURL(FileDomain fileDomain){

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileDomain.getId())
                .toUriString();
    }
}
