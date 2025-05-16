package com.rd.userservice.service;

import com.rd.userservice.log.UserActionLogger;
import com.rd.userservice.model.User;
import com.rd.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserActionLogger userActionLogger;

    @Test
    void save_ShouldReturnSavedUser() {
        User user = User.builder().name("Ali").email("ali@example.com").favoriteGenre("male").build();
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.save(user);

        assertThat(result).isEqualTo(user);
        verify(userRepository).save(user);
    }

    @Test
    void delete_ShouldInvokeRepository() {
        doNothing().when(userRepository).deleteById(1L);

        userService.delete(1L);

        verify(userRepository).deleteById(1L);
    }
}
