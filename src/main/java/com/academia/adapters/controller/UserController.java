package com.academia.adapters.controller;

import com.academia.application.domain.models.User;
import com.academia.application.ports.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity getAllUsers(){

        List<User> users = userService.findAll();
        return new ResponseEntity<>((users), HttpStatus.OK);
    }

    @PostMapping
    //public ResponseEntity<Usuario> sendingEmail(@RequestBody @Valid UsuarioDto usuarioDto) {
    public ResponseEntity<User> save(@RequestBody User user) {
        //Usuario usuario = new Usuario();
        //BeanUtils.copyProperties(usuarioDto, usuario);
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

}
