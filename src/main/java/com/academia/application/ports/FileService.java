package com.academia.application.ports;
import com.academia.application.domain.models.FileDomain;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileService {

    List<FileDomain> getAllFiles();

    FileDomain save(MultipartFile file) throws IOException;
    Optional<FileDomain> getFile(String id);
}
