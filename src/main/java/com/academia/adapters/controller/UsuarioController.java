package com.academia.adapters.controller;

import com.academia.application.domain.models.Usuario;
import com.academia.application.ports.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity getAllUsuarios(){

        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>((usuarios), HttpStatus.OK);
    }
}
