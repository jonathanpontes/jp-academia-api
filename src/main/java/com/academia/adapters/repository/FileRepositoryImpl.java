package com.academia.adapters.repository;

import com.academia.adapters.repository.entity.FileEntity;
import com.academia.adapters.repository.jpainterfaces.FileJpaRepository;
import com.academia.application.domain.models.FileDomain;
import com.academia.application.ports.FileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FileRepositoryImpl implements FileRepository {

    private final FileJpaRepository jpaRepository;

    public FileRepositoryImpl(FileJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<FileDomain> findAll() {

        return jpaRepository.findAll()
                .stream()
                .map(fileEntity -> modelMapper.map(fileEntity, FileDomain.class))
                .collect(Collectors.toList());
    }

    @Override
    public FileDomain save(FileDomain fileDomain) {
        FileEntity fileEntity = jpaRepository.save(modelMapper.map(fileDomain, FileEntity.class));
        return modelMapper.map(fileEntity, FileDomain.class);
    }

    @Override
    public Optional<FileDomain> findById(String id) {
        Optional<FileEntity> fileEntityOptional = jpaRepository.findById(id);

       return fileEntityOptional.isPresent() ?
                Optional.of(modelMapper.map(fileEntityOptional.get(), FileDomain.class)) : Optional.empty();
    }

}