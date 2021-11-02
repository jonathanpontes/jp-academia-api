package com.academia.adapters.controller;

import com.academia.adapters.repository.entity.UserEntity;
import com.academia.application.domain.models.FileDomain;
import com.academia.application.domain.models.User;
import com.academia.application.ports.FileService;
import com.academia.application.ports.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.jackson.JsonNodeValueReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllUsers(){

        List<User> users = userService.findAll();
        return new ResponseEntity<>((users), HttpStatus.OK);
    }

   /* @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }*/

    @PostMapping
    public ResponseEntity<String> save(@RequestParam("userString") String userString, @RequestParam("file") MultipartFile file) throws IOException {


        FileDomain fileDomain = new FileDomain();
        fileDomain.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileDomain.setContentType(file.getContentType());
        fileDomain.setData(file.getBytes());
        fileDomain.setSize(file.getSize());

        modelMapper.getConfiguration().addValueReader(new JsonNodeValueReader());
        modelMapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.UNDERSCORE);
        JsonNode userNode = new ObjectMapper().readTree(userString);

        User user = modelMapper.map(userNode, User.class);

        user.setFileDomain(fileDomain);

        return new ResponseEntity(userService.save(user), HttpStatus.CREATED);
    }
}
