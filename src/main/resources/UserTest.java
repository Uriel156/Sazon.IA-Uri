import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.Proyecto.SazonIA.controller.userProfileController;
import com.Proyecto.SazonIA.model.userProfileModel;
import com.Proyecto.SazonIA.repository.userProfileRepository;
import com.Proyecto.SazonIA.service.userProfileService;

@SpringBootTest
public class UserTest {

    @Autowired
    private userProfileRepository repository;

    @Autowired
    private userProfileService service;

    @Autowired
    private userProfileController controller;

    @Test
    public void testUserProfileCRUD() {
        // Crear un nuevo perfil de usuario
        userProfileModel user = new userProfileModel();
        user.setName("Juan");
        user.setPaternalLastName("Pérez");
        user.setMaternalLastName("García");
        user.setBirthdate(Date.valueOf("1990-01-01"));
        user.setPhoneNumber("1234567890");
        user.setEmail("juan@example.com");
        user.setPassword("password123");

        // Guardar el usuario
        userProfileModel savedUser = service.saveUserProfile(user);
        assertNotNull(savedUser.getUserId());

        // Obtener el usuario
        userProfileModel retrievedUser = service.getUserProfileById(savedUser.getUserId()).orElse(null);
        assertNotNull(retrievedUser);
        assertEquals("Juan", retrievedUser.getName());

        // Actualizar el usuario
        retrievedUser.setName("Juan Carlos");
        userProfileModel updatedUser = service.updateProfile(retrievedUser.getUserId(), retrievedUser);
        assertEquals("Juan Carlos", updatedUser.getName());

        // Eliminar el usuario
        service.deleteUserProfile(updatedUser.getUserId());
        assertFalse(service.getUserProfileById(updatedUser.getUserId()).isPresent());
    }

    @Test
    public void testPagination() {
        // Crear varios usuarios para la prueba
        for (int i = 0; i < 20; i++) {
            userProfileModel user = new userProfileModel();
            user.setName("User " + i);
            user.setEmail("user" + i + "@example.com");
            user.setPassword("password");
            service.saveUserProfile(user);
        }

        // Probar la paginación
        Pageable pageable = PageRequest.of(0, 10);
        Page<userProfileModel> userPage = repository.findAll(pageable);

        assertEquals(10, userPage.getContent().size());
        assertTrue(userPage.hasNext());
        assertFalse(userPage.hasPrevious());

        // Verificar la segunda página
        pageable = PageRequest.of(1, 10);
        userPage = repository.findAll(pageable);

        assertTrue(userPage.getContent().size() > 0);
        assertFalse(userPage.hasNext());
        assertTrue(userPage.hasPrevious());
    }

    @Test
    public void testRelationships() {
        // Crear dos usuarios
        userProfileModel user1 = new userProfileModel();
        user1.setName("User 1");
        user1.setEmail("user1@example.com");
        user1.setPassword("password");
        userProfileModel savedUser1 = service.saveUserProfile(user1);

        userProfileModel user2 = new userProfileModel();
        user2.setName("User 2");
        user2.setEmail("user2@example.com");
        user2.setPassword("password");
        userProfileModel savedUser2 = service.saveUserProfile(user2);

        // User1 sigue a User2
        service.followUser(savedUser2.getUserId(), savedUser1.getUserId());

        // Verificar las relaciones
        userProfileModel updatedUser1 = service.getUserProfileById(savedUser1.getUserId()).orElse(null);
        userProfileModel updatedUser2 = service.getUserProfileById(savedUser2.getUserId()).orElse(null);

        assertNotNull(updatedUser1);
        assertNotNull(updatedUser2);

        assertTrue(updatedUser1.getFollowing().contains(updatedUser2));
        assertTrue(updatedUser2.getFollowers().contains(updatedUser1));
    }
}
