package edu.javeriana.tools.controller;

import edu.javeriana.tools.entity.Tool;
import edu.javeriana.tools.service.ToolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
    void list_ShouldReturnListOfTools() {
        // Arrange
        List<Tool> toolList = new ArrayList<>();
        when(toolService.list()).thenReturn(toolList);

        // Act
        ResponseEntity<List<Tool>> response = toolController.list();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(toolList, response.getBody());
    }

    @Test
    void getById_ExistingId_ShouldReturnTool() {
        // Arrange
        int id = 1;
        Tool tool = new Tool();
        when(toolService.existById(id)).thenReturn(true);
        when(toolService.getOneById(id)).thenReturn(tool);

        // Act
        ResponseEntity<Tool> response = toolController.getById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tool, response.getBody());
    }

    @Test
    void getByName_ExistingName_ShouldReturnTool() {
        // Arrange
        String name = "TestTool";
        Tool tool = new Tool();
        when(toolService.existByName(name)).thenReturn(true);
        when(toolService.getOneByName(name)).thenReturn(tool);

        // Act
        ResponseEntity<Tool> response = toolController.getByName(name);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tool, response.getBody());
    }

    @Test
    void getByName_NonExistingName_ShouldReturnNotFound() {
        // Arrange
        String name = "NonExistingTool";
        when(toolService.existByName(name)).thenReturn(false);

        // Act
        ResponseEntity<Tool> response = toolController.getByName(name);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create_ValidTool_ShouldReturnProductSaved() {
        // Arrange
        Tool tool = new Tool(1, "TestTool", "Test Description", 100, "test.jpg", "Test Country", "Test Cities", 10);
        when(toolService.existByName(tool.getName())).thenReturn(false);

        // Act
        ResponseEntity<?> response = toolController.create(tool);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product saved", response.getBody());
        verify(toolService, times(1)).save(tool);
    }

    @Test
    void create_ToolWithBlankName_ShouldReturnBadRequest() {
        // Arrange
        Tool tool = new Tool(1, "", "Test Description", 100, "test.jpg", "Test Country", "Test Cities", 10);

        // Act
        ResponseEntity<?> response = toolController.create(tool);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Name is required", response.getBody());
        verify(toolService, never()).save(any(Tool.class));
    }

    @Test
    void create_ToolWithNegativePrice_ShouldReturnBadRequest() {
        // Arrange
        Tool tool = new Tool(1, "TestTool", "Test Description", -100, "test.jpg", "Test Country", "Test Cities", 10);

        // Act
        ResponseEntity<?> response = toolController.create(tool);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Price must be greater than zero", response.getBody());
        verify(toolService, never()).save(any(Tool.class));
    }

    @Test
    void create_DuplicateToolName_ShouldReturnBadRequest() {
        // Arrange
        Tool tool = new Tool(1, "TestTool", "Test Description", 100, "test.jpg", "Test Country", "Test Cities", 10);
        when(toolService.existByName(tool.getName())).thenReturn(true);

        // Act
        ResponseEntity<?> response = toolController.create(tool);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Product exist in data base", response.getBody());
        verify(toolService, never()).save(any(Tool.class));
    }

    @Test
    void update_ExistingIdAndValidTool_ShouldReturnProductSaved() {
        // Arrange
        int id = 1;
        Tool tool = new Tool(1, "UpdatedTool", "Updated Description", 200, "updated.jpg", "Updated Country", "Updated Cities", 20);
        when(toolService.existById(id)).thenReturn(true);
        when(toolService.existByName(tool.getName())).thenReturn(false);
        when(toolService.getOneById(id)).thenReturn(new Tool());

        // Act
        ResponseEntity<?> response = toolController.update(id, tool);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product saved", response.getBody());
        verify(toolService, times(1)).save(any(Tool.class));
    }

    @Test
    void update_NonExistingId_ShouldReturnNotFound() {
        // Arrange
        int id = 1;
        Tool tool = new Tool();

        // Act
        ResponseEntity<?> response = toolController.update(id, tool);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("not exist", response.getBody());
        verify(toolService, never()).save(any(Tool.class));
    }

    @Test
    void update_DuplicateToolName_ShouldReturnBadRequest() {
        // Arrange
        int id = 1;
        Tool tool = new Tool(1, "TestTool", "Test Description", 100, "test.jpg", "Test Country", "Test Cities", 10);
        when(toolService.existById(id)).thenReturn(true);
        when(toolService.existByName(tool.getName())).thenReturn(true);
        when(toolService.getOneByName(tool.getName())).thenReturn(new Tool());

        // Act
        ResponseEntity<?> response = toolController.update(id, tool);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("tool exist in database", response.getBody());
        verify(toolService, never()).save(any(Tool.class));
    }

    @Test
    void update_ToolWithBlankName_ShouldReturnBadRequest() {
        // Arrange
        int id = 1;
        Tool tool = new Tool(1, "", "Test Description", 100, "test.jpg", "Test Country", "Test Cities", 10);
        when(toolService.existById(id)).thenReturn(true);
        when(toolService.getOneById(id)).thenReturn(new Tool());

        // Act
        ResponseEntity<?> response = toolController.update(id, tool);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Name is required", response.getBody());
        verify(toolService, never()).save(any(Tool.class));
    }

    @Test
    void update_ToolWithNegativePrice_ShouldReturnBadRequest() {
        // Arrange
        int id = 1;
        Tool tool = new Tool(1, "TestTool", "Test Description", -100, "test.jpg", "Test Country", "Test Cities", 10);
        when(toolService.existById(id)).thenReturn(true);
        when(toolService.getOneById(id)).thenReturn(new Tool());

        // Act
        ResponseEntity<?> response = toolController.update(id, tool);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Price is required", response.getBody());
        verify(toolService, never()).save(any(Tool.class));
    }

    @Test
    void delete_ExistingId_ShouldReturnProductDeleted() {
        // Arrange
        int id = 1;
        when(toolService.existById(id)).thenReturn(true);

        // Act
        ResponseEntity<?> response = toolController.delete(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted", response.getBody());
        verify(toolService, times(1)).delete(id);
    }

    @Test
    void delete_NonExistingId_ShouldReturnNotFound() {
        // Arrange
        int id = 1;
        when(toolService.existById(id)).thenReturn(false);

        // Act
        ResponseEntity<?> response = toolController.delete(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("not exist", response.getBody());
        verify(toolService, never()).delete(id);
    }

    @Test
    void filterByName_ShouldReturnListOfFilteredTools() {
        // Arrange
        String name = "FilterName";
        List<Tool> toolList = new ArrayList<>();
        when(toolService.filterByName(name)).thenReturn(toolList);

        // Act
        ResponseEntity<List<Tool>> response = toolController.filterByName(name);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(toolList, response.getBody());
    }

    @Test
    void filterByBrandId_ShouldReturnListOfFilteredTools() {
        // Arrange
        int brandId = 1;
        List<Tool> toolList = new ArrayList<>();
        when(toolService.filterByBrandId(brandId)).thenReturn(toolList);

        // Act
        ResponseEntity<List<Tool>> response = toolController.filterByBrandId(brandId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(toolList, response.getBody());
    }
}
