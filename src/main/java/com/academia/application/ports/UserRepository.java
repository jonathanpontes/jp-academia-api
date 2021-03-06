package com.academia.application.ports;

import com.academia.application.domain.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    List<User> findAll();
    User save(User user);
    Optional<User> findById(String id);
}
