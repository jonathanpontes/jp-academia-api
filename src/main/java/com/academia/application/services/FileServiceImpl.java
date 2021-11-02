package com.academia.application.services;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.academia.application.domain.models.FileDomain;
import com.academia.application.ports.FileRepository;
import com.academia.application.ports.FileService;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<FileDomain> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public FileDomain save(MultipartFile file) throws IOException {

        FileDomain fileDomain = new FileDomain();
        fileDomain.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileDomain.setContentType(file.getContentType());
        fileDomain.setData(file.getBytes());
        fileDomain.setSize(file.getSize());

        return fileRepository.save(fileDomain);
    }

    @Override
    public Optional<FileDomain> getFile(String id) {
        return fileRepository.findById(id);
    }
}
