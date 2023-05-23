package edu.javeriana.tools.service;

import edu.javeriana.tools.entity.Tool;
import edu.javeriana.tools.repository.ToolRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ToolServiceTest {

    @Mock
    private ToolRepository toolRepository;

    @InjectMocks
    private ToolService toolService;

    @Test
    void filterByBrandId_ShouldReturnFilteredTools() {
        // Arrange
        int brandId = 1;
        List<Tool> tools = new ArrayList<>();
        tools.add(new Tool(1, "Tool1", "Description1", 100, "img1.jpg", "Country1", "Cities1", 10));
        tools.add(new Tool(2, "Tool2", "Description2", 200, "img2.jpg", "Country2", "Cities2", 20));
        when(toolRepository.findAll()).thenReturn(tools);

        // Act
        List<Tool> filteredTools = toolService.filterByBrandId(brandId);

        // Assert
        assertEquals(1, filteredTools.size());
        verify(toolRepository, times(1)).findAll();
    }

    @Test
    void list_ShouldReturnAllTools() {
        // Arrange
        List<Tool> tools = new ArrayList<>();
        tools.add(new Tool(1, "Tool1", "Description1", 100, "img1.jpg", "Country1", "Cities1", 10));
        tools.add(new Tool(2, "Tool2", "Description2", 200, "img2.jpg", "Country2", "Cities2", 20));
        when(toolRepository.findAll()).thenReturn(tools);

        // Act
        List<Tool> allTools = toolService.list();

        // Assert
        assertEquals(2, allTools.size());
        verify(toolRepository, times(1)).findAll();
    }

    @Test
    void getOneById_ExistingId_ShouldReturnTool() {
        // Arrange
        int id = 1;
        Tool tool = new Tool(1, "Tool1", "Description1", 100, "img1.jpg", "Country1", "Cities1", 10);
        when(toolRepository.findById(id)).thenReturn(Optional.of(tool));

        // Act
        Tool result = toolService.getOneById(id);

        // Assert
        assertEquals(tool, result);
        verify(toolRepository, times(1)).findById(id);
    }

    @Test
    void getOneByName_ExistingName_ShouldReturnTool() {
        // Arrange
        String name = "Tool1";
        Tool tool = new Tool(1, "Tool1", "Description1", 100, "img1.jpg", "Country1", "Cities1", 10);
        when(toolRepository.findByName(name)).thenReturn(tool);

        // Act
        Tool result = toolService.getOneByName(name);

        // Assert
        assertEquals(tool, result);
        verify(toolRepository, times(1)).findByName(name);
    }

    @Test
    void getOneByName_NonExistingName_ShouldReturnNull() {
        // Arrange
        String name = "Tool1";
        when(toolRepository.findByName(name)).thenReturn(null);

        // Act
        Tool result = toolService.getOneByName(name);

        // Assert
        assertEquals(null, result);
        verify(toolRepository, times(1)).findByName(name);
    }

    @Test
    void save_ShouldCallRepositorySaveMethod() {
        // Arrange
        Tool tool = new Tool(1, "Tool1", "Description1", 100, "img1.jpg", "Country1", "Cities1", 10);

        // Act
        toolService.save(tool);

        // Assert
        verify(toolRepository, times(1)).save(tool);
    }

    @Test
    void delete_ShouldCallRepositoryDeleteByIdMethod() {
        // Arrange
        int id = 1;

        // Act
        toolService.delete(id);

        // Assert
        verify(toolRepository, times(1)).deleteById(id);
    }

    @Test
    void existById_ExistingId_ShouldReturnTrue() {
        // Arrange
        int id = 1;
        when(toolRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = toolService.existById(id);

        // Assert
        assertEquals(true, result);
        verify(toolRepository, times(1)).existsById(id);
    }

    @Test
    void existById_NonExistingId_ShouldReturnFalse() {
        // Arrange
        int id = 1;
        when(toolRepository.existsById(id)).thenReturn(false);

        // Act
        boolean result = toolService.existById(id);

        // Assert
        assertEquals(false, result);
        verify(toolRepository, times(1)).existsById(id);
    }

    @Test
    void existByName_ExistingName_ShouldReturnTrue() {
        // Arrange
        String name = "Tool1";
        when(toolRepository.findByName(name)).thenReturn(new Tool());

        // Act
        boolean result = toolService.existByName(name);

        // Assert
        assertEquals(true, result);
        verify(toolRepository, times(1)).findByName(name);
    }

    @Test
    void existByName_NonExistingName_ShouldReturnFalse() {
        // Arrange
        String name = "Tool1";
        when(toolRepository.findByName(name)).thenReturn(null);

        // Act
        boolean result = toolService.existByName(name);

        // Assert
        assertEquals(false, result);
        verify(toolRepository, times(1)).findByName(name);
    }

    @Test
    void findAllPaged_ShouldReturnPagedTools() {
        // Arrange
        Pageable pageable = mock(Pageable.class);
        Page<Tool> pagedTools = mock(Page.class);
        when(toolRepository.findAll(pageable)).thenReturn(pagedTools);

        // Act
        Page<Tool> result = toolService.findAllPaged(pageable);

        // Assert
        assertEquals(pagedTools, result);
        verify(toolRepository, times(1)).findAll(pageable);
    }
}
