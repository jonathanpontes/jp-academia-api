package com.academia.application.ports;

import com.academia.application.domain.models.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();
    User save(User user);
}
