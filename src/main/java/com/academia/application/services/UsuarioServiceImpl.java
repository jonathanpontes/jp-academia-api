package com.academia.application.services;

import com.academia.application.domain.models.Usuario;
import com.academia.application.ports.UsuarioRepository;
import com.academia.application.ports.UsuarioService;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }
}
