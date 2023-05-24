package edu.javeriana.users.control;

import edu.javeriana.users.entity.User;
import edu.javeriana.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testHola() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hola"));
    }

    @Test
    public void testList() throws Exception {
        User user1 = new User("John Doe", "john.doe@example.com", "password1", "token1", new Date(), 1, true, true);
        User user2 = new User("Jane Smith", "jane.smith@example.com", "password2", "token2", new Date(), 2, false, true);
        List<User> userList = Arrays.asList(user1, user2);

        Mockito.when(userService.list()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("jane.smith@example.com"));
    }

    @Test
    public void testGetById() throws Exception {
        int userId = 1;
        User user = new User("John Doe", "john.doe@example.com", "password1", "token1", new Date(), 1, true, true);

        Mockito.when(userService.existById(userId)).thenReturn(true);
        Mockito.when(userService.getOneById(userId)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/detail/{id}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testGetById_NotFound() throws Exception {
        int userId = 1;

        Mockito.when(userService.existById(userId)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/detail/{id}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetByEmail() throws Exception {
        String email = "john.doe@example.com";
        User user = new User("John Doe", email, "password1", "token1", new Date(), 1, true, true);

        Mockito.when(userService.existsByEmail(email)).thenReturn(true);
        Mockito.when(userService.getOneByEmail(email)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/detailname/{email}", email)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(email));
    }

    @Test
    public void testGetByEmail_NotFound() throws Exception {
        String email = "john.doe@example.com";

        Mockito.when(userService.existsByEmail(email)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/detailname/{email}", email)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testLogin() throws Exception {
        String email = "john.doe@example.com";
        String password = "password1";
        User user = new User("John Doe", email, password, "token1", new Date(), 1, true, true);

        Mockito.when(userService.existsByEmail(email)).thenReturn(true);
        Mockito.when(userService.getOneByEmail(email)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login/{email}&{password}", email, password)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Acceso concedido"));
    }

    @Test
    public void testLogin_InvalidEmail() throws Exception {
        String email = "john.doe@example.com";
        String password = "password1";

        Mockito.when(userService.existsByEmail(email)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login/{email}&{password}", email, password)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testLogin_IncorrectPassword() throws Exception {
        String email = "john.doe@example.com";
        String password = "password1";
        User user = new User("John Doe", email, "wrongpassword", "token1", new Date(), 1, true, true);

        Mockito.when(userService.existsByEmail(email)).thenReturn(true);
        Mockito.when(userService.getOneByEmail(email)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login/{email}&{password}", email, password)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void testCreate() throws Exception {
        User user = new User("John Doe", "john.doe@example.com", "password1", "token1", new Date(), 1, true, true);

        Mockito.when(userService.existsByEmail(user.getEmail())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"password\":\"password1\",\"token\":\"token1\",\"birthday\":null,\"id_city\":1,\"admin\":true,\"active\":true}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Usuario guardado"));
    }

    @Test
    public void testCreate_BlankEmail() throws Exception {
        User user = new User("John Doe", "", "password1", "token1", new Date(), 1, true, true);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"email\":\"\",\"password\":\"password1\",\"token\":\"token1\",\"birthday\":null,\"id_city\":1,\"admin\":true,\"active\":true}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testCreate_DuplicateUser() throws Exception {
        String email = "john.doe@example.com";
        User user = new User("John Doe", email, "password1", "token1", new Date(), 1, true, true);

        Mockito.when(userService.existsByEmail(email)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"email\":\"" + email + "\",\"password\":\"password1\",\"token\":\"token1\",\"birthday\":null,\"id_city\":1,\"admin\":true,\"active\":true}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testUpdate() throws Exception {
        int userId = 1;
        User user = new User("John Doe", "john.doe@example.com", "password1", "token1", new Date(), 1, true, true);

        Mockito.when(userService.existById(userId)).thenReturn(true);
        Mockito.when(userService.existsByEmail(user.getEmail())).thenReturn(false);
        Mockito.when(userService.getOneById(userId)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/update/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"password\":\"password1\",\"token\":\"token1\",\"birthday\":null,\"id_city\":1,\"admin\":true,\"active\":true}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Usuario guardado"));
    }

    @Test
    public void testUpdate_NotFound() throws Exception {
        int userId = 1;

        Mockito.when(userService.existById(userId)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/update/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"password\":\"password1\",\"token\":\"token1\",\"birthday\":null,\"id_city\":1,\"admin\":true,\"active\":true}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testUpdate_DuplicateUser() throws Exception {
        int userId = 1;
        String email = "john.doe@example.com";
        User user = new User("John Doe", email, "password1", "token1", new Date(), 1, true, true);

        Mockito.when(userService.existById(userId)).thenReturn(true);
        Mockito.when(userService.existsByEmail(user.getEmail())).thenReturn(true);
        Mockito.when(userService.getOneByEmail(user.getEmail())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/update/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"email\":\"" + email + "\",\"password\":\"password1\",\"token\":\"token1\",\"birthday\":null,\"id_city\":1,\"admin\":true,\"active\":true}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testUpdate_BlankEmail() throws Exception {
        int userId = 1;
        User user = new User("John Doe", "", "password1", "token1", new Date(), 1, true, true);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/update/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"email\":\"\",\"password\":\"password1\",\"token\":\"token1\",\"birthday\":null,\"id_city\":1,\"admin\":true,\"active\":true}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testDelete() throws Exception {
        int userId = 1;

        Mockito.when(userService.existById(userId)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/delete/{id}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Usuario eliminado"));
    }

    @Test
    public void testDelete_NotFound() throws Exception {
        int userId = 1;

        Mockito.when(userService.existById(userId)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/delete/{id}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
