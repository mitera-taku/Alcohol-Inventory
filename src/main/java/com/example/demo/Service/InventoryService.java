package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Domain.Inventory;
import com.example.demo.Domain.ProductInventory;
import com.example.demo.Domain.Products;
import com.example.demo.Repository.InventoryRepository;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory getInventory(int id) {
        return inventoryRepository.findById(id);
    }

    public void addInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public void updateInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public Inventory deleteInventory(int id) {
        return inventoryRepository.deleteById(id);
    }

    public List<Products> allInventory() {
        List<Products> inventory = inventoryRepository.findAll();
        return inventory;
    }

    public List<ProductInventory> allProductsWithInventory() {
        List<ProductInventory> productInventory = inventoryRepository.getAllProductsWithInventory();
        return productInventory;
    }
}
