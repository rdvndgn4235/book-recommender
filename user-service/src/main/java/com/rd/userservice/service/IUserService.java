package com.rd.userservice.service;

import com.rd.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User save(User user);

    User updateUser(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void delete(Long id);

    void clearAllCache();
}
