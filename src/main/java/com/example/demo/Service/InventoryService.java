package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Domain.Inventory;
import com.example.demo.Repository.InventoryRepository;

public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory getInventory(int id) {
        return inventoryRepository.findById(id);
    }

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory deleteInventory(int id) {
        return inventoryRepository.deleteById(id);
    }
}
