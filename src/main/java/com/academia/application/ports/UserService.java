package com.academia.application.ports;

import com.academia.application.domain.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User save(User user);
}
