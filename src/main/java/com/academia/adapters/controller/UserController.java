package com.academia.adapters.controller;

import com.academia.adapters.dtos.UserDto;
import com.academia.adapters.utils.FileHandlerDomain;
import com.academia.application.domain.models.FileDomain;
import com.academia.application.domain.models.User;
import com.academia.application.ports.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.jackson.JsonNodeValueReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllUsers(){

        List<UserDto> usersDto = userService.findAll()
                .stream()
                .map(userReturned -> {
                    UserDto userDto = modelMapper.map(userReturned, UserDto.class);
                    userDto.setImageDownloadURL(FileHandlerDomain.getDownloadURL(userReturned.getFileDomain()));
                    return userDto;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>((usersDto), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<UserDto> save(@RequestParam("userString") String userString, @RequestParam("file") MultipartFile file) throws IOException {

        //Convert type and set properties
        JsonNode userNode = new ObjectMapper().readTree(userString);
        User user = modelMapper.map(userNode, User.class);
        user.setFileDomain(new FileDomain(file));

        //saved
        User userReturned = userService.save(user);

        //Convert to dto
        UserDto userDto = modelMapper.map(userReturned, UserDto.class);
        userDto.setImageDownloadURL(FileHandlerDomain.getDownloadURL(userReturned.getFileDomain()));

        return new ResponseEntity(userDto, HttpStatus.CREATED);
    }

}
