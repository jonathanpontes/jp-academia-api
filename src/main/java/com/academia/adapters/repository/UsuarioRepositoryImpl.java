package com.academia.adapters.repository;

import com.academia.application.domain.models.Usuario;
import com.academia.application.ports.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository jpaRepository;

    public UsuarioRepositoryImpl(UsuarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Usuario> findAll() {

        return jpaRepository.findAll()
                .stream()
                .map(usuarioEntity -> modelMapper.map(usuarioEntity, Usuario.class))
                .collect(Collectors.toList());
    }
}
