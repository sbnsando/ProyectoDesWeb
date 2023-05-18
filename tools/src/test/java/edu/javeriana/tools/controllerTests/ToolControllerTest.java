package edu.javeriana.tools.controllerTests;

import edu.javeriana.tools.controller.ToolController;
import edu.javeriana.tools.entity.Tool;
import edu.javeriana.tools.service.ToolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ToolControllerTest {

    @Mock
    private ToolService toolService;

    @InjectMocks
    private ToolController toolController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListTools() throws Exception {
        List<Tool> expectedList = Arrays.asList(new Tool(), new Tool());
        when(toolService.list()).thenReturn(expectedList);

        // Act
        ResponseEntity<List<Tool>> response = toolController.list();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedList, response.getBody());

    }

    @Test
    public void testGetToolById() throws Exception {


    }

    @Test
    void hola() {
    }

    @Test
    void list() {
    }

    @Test
    void getById() {
    }

    @Test
    void getByName() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void filterByName() {
    }

    @Test
    void filterByBrandId() {
    }

    @Test
    void searchPaged() {
    }
}