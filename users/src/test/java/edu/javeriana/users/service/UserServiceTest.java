package edu.javeriana.users.service;

import edu.javeriana.users.entity.User;
import edu.javeriana.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testList() {
        List<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.list();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetOneById() {
        int userId = 1;
        User user = new User("John Doe", "john@example.com", "password", "token", new Date(), 1, false, true);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getOneById(userId);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetOneByEmail() {
        String email = "john@example.com";
        User user = new User("John Doe", email, "password", "token", new Date(), 1, false, true);
        when(userRepository.findByEmail(email)).thenReturn(user);

        User result = userService.getOneByEmail(email);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testSave() {
        User user = new User("John Doe", "john@example.com", "password", "token", new Date(), 1, false, true);

        userService.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDelete() {
        int userId = 1;

        userService.delete(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testExistById() {
        int userId = 1;
        when(userRepository.existsById(userId)).thenReturn(true);

        boolean result = userService.existById(userId);

        assertTrue(result);
        verify(userRepository, times(1)).existsById(userId);
    }

    @Test
    public void testExistsByEmail() {
        String email = "john@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        boolean result = userService.existsByEmail(email);

        assertTrue(result);
        verify(userRepository, times(1)).existsByEmail(email);
    }

    // Prueba para el método findAllPaged
    @Test
    public void testFindAllPaged() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> expectedPage = new PageImpl<>(Collections.emptyList());
        when(userRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<User> result = userService.findAllPaged(pageable);

        assertNotNull(result);
        assertEquals(expectedPage, result);
        verify(userRepository, times(1)).findAll(pageable);
    }
}
