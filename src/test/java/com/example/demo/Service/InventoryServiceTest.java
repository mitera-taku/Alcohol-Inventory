package com.example.demo.Service;

import com.example.demo.Form.Inventory;
import com.example.demo.Repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllInventory() {
        List<Inventory> mockInventory = Arrays.asList(
            new Inventory(1, "Product A", 10, 100),
            new Inventory(2, "Product B", 20, 200)
        );

        when(inventoryRepository.findAll()).thenReturn(mockInventory);

        List<Inventory> inventoryList = inventoryService.allInventory();
        assertEquals(2, inventoryList.size());
        assertEquals("Product A", inventoryList.get(0).getName());
    }

    @Test
    void testExportInventoryToCSV() throws IOException {
        // Mock the repository and the data
        Inventory inventory1 = new Inventory(1, "Product A", 10, 100);
        Inventory inventory2 = new Inventory(2, "Product B", 20, 200);
        List<Inventory> mockInventory = Arrays.asList(inventory1, inventory2);

        when(inventoryRepository.findAll()).thenReturn(mockInventory);

        // Test export to CSV
        inventoryService.exportInventoryToCSV("test.csv");

        // Verify that the repository was accessed
        verify(inventoryRepository, times(1)).findAll();
    }
}
