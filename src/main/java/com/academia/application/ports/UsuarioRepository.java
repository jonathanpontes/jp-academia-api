package com.academia.application.ports;

import com.academia.application.domain.models.Usuario;

import java.util.List;

public interface UsuarioRepository {

    List<Usuario> findAll();
    Usuario save(Usuario usuario);
}
