package com.academia.adapters.repository;

import com.academia.adapters.repository.entity.UserEntity;
import com.academia.adapters.repository.jpainterfaces.UserJpaRepository;
import com.academia.application.domain.models.User;
import com.academia.application.ports.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryImpl(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<User> findAll() {

        return jpaRepository.findAll()
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, User.class))
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = jpaRepository.save(modelMapper.map(user, UserEntity.class));
        return modelMapper.map(userEntity, User.class);
    }
}
