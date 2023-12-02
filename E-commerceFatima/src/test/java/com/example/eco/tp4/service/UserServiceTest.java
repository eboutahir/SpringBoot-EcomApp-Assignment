import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.eco.tp4.service.UserService;
import com.example.eco.tp4.domain.User;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByUsername() {
        // Créer un utilisateur fictif pour le test
        User user = new User();
        user.setId(1L);
        user.setUsername("fatima");
        Mockito.when(userRepository.findByUsername("fatima")).thenReturn(user);

        // Appeler la méthode findByUsername du service
        User result = userService.findByUsername("fatima");

        // Vérifier si le résultat est correct
        assertEquals(1L, result.getId());
        assertEquals("fatima", result.getUsername());
    }
    @Test
    void testFindById() {
        // Créer un utilisateur fictif pour le test
        User user = new User();
        user.setId(1L);
        user.setUsername("fatima");
        Mockito.when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        // Appeler la méthode findById du service
        User result = userService.findById(1L);

        // Vérifier si le résultat est correct
        assertEquals(1L, result.getId());
        assertEquals("fatima", result.getUsername());
    }



    @Test
    void testFindByEmail() {
        // Créer un utilisateur fictif pour le test
        User user = new User();
        user.setId(1L);
        user.setUsername("fatima");
        user.setEmail("f@gmail.com");
        Mockito.when(userRepository.findByEmail("f@gmail.com")).thenReturn(user);

        // Appeler la méthode findByEmail du service
        User result = userService.findByEmail("f@gmail.com");

        // Vérifier si le résultat est correct
        assertEquals(1L, result.getId());
        assertEquals("fatima", result.getUsername());
        assertEquals("f@gmail.com", result.getEmail());
    }

