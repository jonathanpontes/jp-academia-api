package com.academia.application.services;

import com.academia.application.domain.models.User;
import com.academia.application.ports.UserRepository;
import com.academia.application.ports.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return repository.findById(id);
    }
}
