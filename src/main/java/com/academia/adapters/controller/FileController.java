package com.academia.adapters.controller;

import com.academia.adapters.dtos.FileDto;
import com.academia.application.domain.models.FileDomain;
import com.academia.application.ports.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity<String> save(@RequestParam("file") MultipartFile file) {
        try {
            FileDomain fileDomain = fileService.save(file);

            return new ResponseEntity<>(fileDomain.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Não foi possivel carregar o arquivo!: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping
    public List<FileDto> list() {
        return fileService.getAllFiles()
                .stream()
                .map(this::mapToFileResponse)
                .collect(Collectors.toList());
    }

    private FileDto mapToFileResponse(FileDomain fileDomain) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileDomain.getId())
                .toUriString();

        FileDto fileDto = new FileDto();
        fileDto.setId(fileDomain.getId());
        fileDto.setName(fileDomain.getName());
        fileDto.setContentType(fileDomain.getContentType());
        fileDto.setSize(fileDomain.getSize());
        fileDto.setUrl(downloadURL);

        return fileDto;
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Optional<FileDomain> fileDomainOptional = fileService.getFile(id);

        if (!fileDomainOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        FileDomain fileDomain = fileDomainOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDomain.getName() + "\"")
                .contentType(MediaType.valueOf(fileDomain.getContentType()))
                .body(fileDomain.getData());
    }
}