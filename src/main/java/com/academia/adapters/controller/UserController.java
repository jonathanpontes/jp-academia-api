package com.academia.adapters.controller;

import com.academia.adapters.dtos.UserDto;
import com.academia.adapters.utils.FileDomainHandler;
import com.academia.application.domain.models.FileDomain;
import com.academia.application.domain.models.User;
import com.academia.application.ports.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
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
                .map(userReturned -> getUserDto(userReturned))
                .collect(Collectors.toList());

        return new ResponseEntity<>((usersDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestParam String userString, @RequestParam(required=false) MultipartFile file) throws IOException {

        //Convert type and set properties
        JsonNode userNode = new ObjectMapper().readTree(userString);
        User user = modelMapper.map(userNode, User.class);

        if(file != null){
            user.setFileDomain(new FileDomain(file));
        }

        //Saved and Convert to dto
        return new ResponseEntity(getUserDto(userService.save(user)), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity getUser(@PathVariable String id){

        if (!userService.findById(id).isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        return new ResponseEntity<>((getUserDto(userService.findById(id).get())), HttpStatus.OK);
    }

    private UserDto getUserDto(User userReturned) {
        UserDto userDto = modelMapper.map(userReturned, UserDto.class);

        if (userReturned.getFileDomain() != null) {
            userDto.setImageDownloadURL(FileDomainHandler.getDownloadURL(userReturned.getFileDomain()));
        } else {
            userDto.setImageDownloadURL("");
        }

        return userDto;
    }

}
