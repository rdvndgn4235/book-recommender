package com.rd.userservice.service;

import com.rd.userservice.dto.UserDto;
import com.rd.userservice.exception.ResourceNotFoundException;
import com.rd.userservice.log.UserActionLogger;
import com.rd.userservice.mapper.UserMapper;
import com.rd.userservice.model.User;
import com.rd.userservice.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserActionLogger userActionLogger;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserActionLogger userActionLogger, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userActionLogger = userActionLogger;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public UserDto save(User user) {
        User savedUser = userRepository.save(user);
        userActionLogger.logAction("CREATE", savedUser.getId(), "System");
        return userMapper.toDto(savedUser);
    }

    @Transactional
    @CachePut(value = "users", key = "#user.id")
    @Override
    public UserDto updateUser(UserDto user) {
        User savedUser = userRepository.save(userMapper.toEntity(user));
        userActionLogger.logAction("UPDATE", savedUser.getId(), "System");
        return userMapper.toDto(savedUser);
    }


    @Cacheable(value = "users", key = "#id")
    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Transactional
    @CacheEvict(value = "users", key = "#id")
    @Override
    public void delete(Long id) {
        userActionLogger.logAction("DELETE", id, "System");
        userRepository.deleteById(id);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void clearAllCache() {
        //sadece cache siler
    }

}
