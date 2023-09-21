package fastrack.stardrinks.services;

import fastrack.stardrinks.dto.RegisterDTO;
import fastrack.stardrinks.model.User;
import fastrack.stardrinks.repository.UserDAO;
import fastrack.stardrinks.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDAO userDAO;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Getting a user ID with success")
    public void testGetUserById() {
        // Arrange
        int userId = 1;
        User user = new User();
        user.setId(userId);
        user.setFirstName("test");
        user.setLastName("user");

        // Mock the UserRepository to return the user when findById is called
        when(userDAO.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertEquals(userId, result.getId());
        assertEquals("test", result.getFirstName());
        assertEquals("user", result.getLastName());


        // Verify that the findById method was called with the expected ID
        verify(userDAO, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Getting user by ID and failure")
    public void testGetUserById_UserNotFound() {
        // Arrange
        int userId = 1;

        // Mock the UserRepository to return an empty Optional, simulating a user not found
        when(userDAO.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        // In this case, you might handle the exception or return a default user
        // We assume here that the service method throws a UsernameNotFoundException when the user is not found
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            User result = userService.getUserById(userId);
        });

        String expectedMessage = "Could not find user using provided id: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        // Verify that the findById method was called with the expected ID
        verify(userDAO, times(1)).findById(userId);
    }

    @Test
    @DisplayName("Saving a user with success")
    public void testSaveUser() {
        // Arrange
        RegisterDTO registerDTO = RegisterDTO.builder().build();
        registerDTO.setFirstName("John");
        registerDTO.setLastName("Doe");
        registerDTO.setEmail("johndoe@example.com");
        registerDTO.setPassword("password");

        // Mock the UserRepository to return the saved user when save is called
        when(userDAO.save(any(User.class))).thenReturn(User.builder().id(1).firstName(registerDTO.getFirstName()).lastName(registerDTO.getLastName()).email(registerDTO.getEmail()).password(registerDTO.getPassword()).build());

        // Act
        User result = userService.save(registerDTO);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("johndoe@example.com", result.getEmail());
        assertEquals(1, result.getId());
        // Add more assertions as needed

        // Verify that the save method was called
        verify(userDAO, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Finding a user by email with success")
    public void testFindUserByEmail() {
        // Arrange
        String email = "johndoe@example.com";
        User user = new User();
        user.setId(1);
        user.setEmail(email);

        // Mock the UserRepository to return the user when findByEmail is called
        when(userDAO.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        User result = userService.findByEmail(email);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        // Add more assertions as needed

        // Verify that the findByEmail method was called with the expected email
        verify(userDAO, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Finding a user by email and failure")
    public void testFindUserByEmail_UserNotFound() {
        // Arrange
        String email = "nonexistent@example.com";

        // Mock the UserRepository to return an empty Optional, simulating a user not found
        when(userDAO.findByEmail(email)).thenReturn(Optional.empty());

        // Act and Assert
        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            User result = userService.findByEmail(email);
        });

        String expectedMessage = "Could not find user using provided email: nonexistent@example.com";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        // Verify that the findByEmail method was called with the expected email
        verify(userDAO, times(1)).findByEmail(email);
    }

}
