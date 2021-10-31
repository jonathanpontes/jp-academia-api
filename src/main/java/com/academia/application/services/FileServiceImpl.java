package com.academia.application.services;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.academia.application.domain.models.FileDomain;
import com.academia.application.ports.FileRepository;
import com.academia.application.ports.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;

    public FileServiceImpl(FileRepository fileRepository, ModelMapper modelMapper) {
        this.fileRepository = fileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FileDomain> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public void save(MultipartFile file) {

        modelMapper.typeMap(MultipartFile.class, FileDomain.class).addMappings(
                mapper -> {
                            mapper.map(mFile ->StringUtils.cleanPath(mFile.getOriginalFilename()),
                                    FileDomain::setName);
                            mapper.map(mFile -> {
                                        try {
                                            return mFile.getBytes();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        return null;
                                    },
                                    FileDomain::setData);
                });

        FileDomain fileDomain = modelMapper.map(file, FileDomain.class);

        /*FileDomain fileDomain = new FileDomain();
        fileDomain.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileDomain.setContentType(file.getContentType());
        fileDomain.setData(file.getBytes());
        fileDomain.setSize(file.getSize());*/

        fileRepository.save(fileDomain);
    }

    @Override
    public Optional<FileDomain> getFile(String id) {
        return fileRepository.findById(id);
    }
}
