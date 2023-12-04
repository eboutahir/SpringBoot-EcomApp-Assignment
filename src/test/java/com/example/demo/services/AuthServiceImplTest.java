package com.example.demo.services;
import com.example.demo.dtos.SignupRequest;
import com.example.demo.dtos.UserDTO;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class AuthServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    public void testCreateUser() {
        // Créez un objet SignupRequest fictif pour le test
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setName("fatima");
        signupRequest.setEmail("fatima@gmail.com");
        signupRequest.setPhone("123456789");
        signupRequest.setPassword("1234");

        // Simulez le comportement du userRepository.save
        Mockito.when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1L); // Simulez l'attribution d'un identifiant par la base de données
            return savedUser;
        });

        // Appelez la méthode à tester
        UserDTO result = authService.createUser(signupRequest);

        // Vérifiez que le résultat est conforme aux attentes
        assertEquals("fatima", result.getName());
        assertEquals("fatima@gmail.com", result.getEmail());
        assertEquals("123456789", result.getPhone());

        // Assurez-vous que la méthode save du userRepository a été appelée avec l'objet User approprié
        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }
    @Test
    public void testCreateUser_Success() {
        // Créez un objet SignupRequest fictif pour le test
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setName("fatima");
        signupRequest.setEmail("fatima@gmail.com");
        signupRequest.setPhone("123456789");
        signupRequest.setPassword("1234");

        // Simulez le comportement du userRepository.save
        Mockito.when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1L); // Simulez l'attribution d'un identifiant par la base de données
            return savedUser;
        });

        // Appelez la méthode à tester
        UserDTO result = authService.createUser(signupRequest);

        // Vérifiez que le résultat est conforme aux attentes
        assertEquals("fatima", result.getName());
        assertEquals("fatima@gmail.com", result.getEmail());
        assertEquals("123456789", result.getPhone());

        // Assurez-vous que la méthode save du userRepository a été appelée avec l'objet User approprié
        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    @Test
    public void testCreateUser_UserAlreadyExists() {
        // Créez un objet SignupRequest fictif pour le test
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setName("existingUser");
        signupRequest.setEmail("existingUser@gmail.com");
        signupRequest.setPhone("123456789");
        signupRequest.setPassword("1234");

        // Simulez le comportement du userRepository.findFirstByEmail
        Mockito.when(userRepository.findFirstByEmail("existingUser@gmail.com"))
                .thenReturn(new User());

        // Appelez la méthode à tester et attendez une exception
        assertThrows(IllegalArgumentException.class, () -> authService.createUser(signupRequest));

        // Assurez-vous que la méthode findFirstByEmail du userRepository a été appelée avec l'email correct
        Mockito.verify(userRepository, Mockito.times(1)).findFirstByEmail("existingUser@gmail.com");
    }

}
