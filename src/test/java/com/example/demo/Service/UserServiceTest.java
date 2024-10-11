package com.example.demo.Service;

import com.example.demo.Domain.User;
import com.example.demo.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        User user = new User(1, "Test User", "testuser@example.com","test" );
        userService.saveUser(user);

        verify(userRepository, times(1)).saveUser(user);
    }

    @Test
    void testGetUserById() {
        User mockUser = new User(1, "Test User", "testuser@example.com","test");

        when(userRepository.getUserById(1)).thenReturn(mockUser);

        User retrievedUser = userService.getUserById(1);
        assertEquals("Test User", retrievedUser.getUsername());
        assertEquals("testuser@example.com", retrievedUser.getEmail());
        assertEquals("test", retrievedUser.getPassword());
    }
}
