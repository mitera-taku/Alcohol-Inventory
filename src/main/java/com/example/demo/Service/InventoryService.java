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

    public void addInventory(ProductInventory productInventory) {
        inventoryRepository.save(productInventory);
    }

    public void updateInventory(ProductInventory productInventory) {
        inventoryRepository.save(productInventory);
    }

    public boolean deleteInventory(int id) {
        return inventoryRepository.deleteByProductId(id);
    }
    
    public boolean checkInventory(int productId) {
        Inventory inventory = inventoryRepository.findById(productId);
        if (inventory != null) {
            return inventoryRepository.deleteByProductId(productId);
        }
        return false; // Add this line to return false if the inventory is null
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
