package com.rd.userservice.service;

import com.rd.userservice.dto.UserDto;
import com.rd.userservice.model.User;

import java.util.List;

public interface IUserService {

    UserDto save(User user);

    UserDto updateUser(UserDto user);

    UserDto findById(Long id);

    List<UserDto> findAll();

    void delete(Long id);

    void clearAllCache();
}
