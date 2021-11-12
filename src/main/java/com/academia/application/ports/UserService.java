package com.academia.application.ports;

import com.academia.application.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    User save(User user);
    public Optional<User> findById(String id);
}
