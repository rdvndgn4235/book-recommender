package com.rd.userservice.service;

import com.rd.userservice.log.UserActionLogger;
import com.rd.userservice.model.User;
import com.rd.userservice.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserActionLogger userActionLogger;

    public UserService(UserRepository userRepository,
                       UserActionLogger userActionLogger) {
        this.userRepository = userRepository;
        this.userActionLogger = userActionLogger;
    }

    @Override
    public User save(User user) {
        User savedUser = userRepository.save(user);
        userActionLogger.logAction("CREATE", savedUser.getId(), "System");
        return savedUser;
    }

    @CachePut(value = "users", key = "#user.id")
    public User updateUser(User user) {
        User savedUser = userRepository.save(user);
        userActionLogger.logAction("UPDATE", savedUser.getId(), "System");
        return savedUser;
    }


    @Cacheable(value = "users", key = "#id")
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @CacheEvict(value = "users", key = "#id")
    @Override
    public void delete(Long id) {
        userActionLogger.logAction("DELETE", id, "System");
        userRepository.deleteById(id);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void clearAllCache() {
        //sadece cache siler
    }

}
