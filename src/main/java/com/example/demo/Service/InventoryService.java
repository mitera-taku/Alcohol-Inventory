package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Domain.inventory;
import com.example.demo.Repository.InventoryRepository;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public inventory getInventory(int id) {
        return inventoryRepository.findById(id);
    }

    public void addInventory(inventory Inventory) {
    inventoryRepository.save(Inventory);
    }

    public void updateInventory(inventory Inventory) {
    inventoryRepository.update(Inventory);
    }

    public boolean deleteInventory(int id) {
        return inventoryRepository.deleteById(id);
    }

    public boolean checkInventory(int id) {
        inventory inventory = inventoryRepository.findById(id);
        if (inventory != null) {
            return inventoryRepository.deleteById(id);
        }
        return false; // Add this line to return false if the inventory is null
    }

    public List<inventory> allInventory() {
        List<inventory> inventory = inventoryRepository.findAll();
        return inventory;
    }
}
