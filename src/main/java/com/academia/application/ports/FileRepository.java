package com.academia.application.ports;

import com.academia.adapters.repository.entity.FileEntity;
import com.academia.application.domain.models.FileDomain;

import java.util.List;
import java.util.Optional;

public interface FileRepository {

    List<FileDomain> findAll();
    FileDomain save(FileDomain fileDomain);

    Optional<FileDomain> findById(String id);
}
