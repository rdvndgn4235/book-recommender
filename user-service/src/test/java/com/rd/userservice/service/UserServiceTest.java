package com.rd.userservice.service;

import com.rd.userservice.dto.UserDto;
import com.rd.userservice.exception.ResourceNotFoundException;
import com.rd.userservice.mapper.UserMapper;
import com.rd.userservice.model.User;
import com.rd.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserById_found() {
        User user = new User();
        user.setName("name");
        user.setUsername("johndoe");
        user.setPassword("password");
        UserDto userDto = new UserDto("name",
                "johndoe",
                "password",
                "example@example.com",
                "USER");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto result = userService.findById(1L);

        assertNotNull(result);
        assertEquals("johndoe", result.username());
    }

    @Test
    public void testGetUserById_notFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> userService.findById(2L));

        assertEquals("User not found with id: 2", exception.getMessage());
    }
}
