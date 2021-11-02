package com.academia.adapters.repository;

import com.academia.adapters.repository.entity.FileEntity;
import com.academia.adapters.repository.entity.UserEntity;
import com.academia.adapters.repository.jpainterfaces.UserJpaRepository;
import com.academia.application.domain.models.FileDomain;
import com.academia.application.domain.models.User;
import com.academia.application.ports.FileRepository;
import com.academia.application.ports.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
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
                .map(userEntity -> getUser(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {

        //Convert to entity class
        UserEntity userEntity = getUserEntity(user);

        //Saved and Convert to domain class
        return getUser(jpaRepository.save(userEntity));
    }

    private UserEntity getUserEntity(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setFileEntity(modelMapper.map(user.getFileDomain(), FileEntity.class));
        return userEntity;
    }

    private User getUser(UserEntity userEntity) {
        User userReturned = modelMapper.map(userEntity, User.class);
        userReturned.setFileDomain(modelMapper.map(userEntity.getFileEntity(), FileDomain.class));
        return userReturned;
    }
}
