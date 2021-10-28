package com.academia.adapters.controller;

import com.academia.application.domain.models.Usuario;
import com.academia.application.ports.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity getAllUsuarios(){

        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>((usuarios), HttpStatus.OK);
    }

    @PostMapping
    //public ResponseEntity<Usuario> sendingEmail(@RequestBody @Valid UsuarioDto usuarioDto) {
    public ResponseEntity<Usuario> sendingEmail(@RequestBody Usuario usuario) {
        //Usuario usuario = new Usuario();
        //BeanUtils.copyProperties(usuarioDto, usuario);
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

}
