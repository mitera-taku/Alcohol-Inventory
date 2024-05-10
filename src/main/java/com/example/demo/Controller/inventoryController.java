package com.example.demo.Controller;

import com.example.demo.Domain.Inventory;
import com.example.demo.Domain.ProductInventory;
import com.example.demo.Service.InventoryService;

import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("")
public class inventoryController {
    @Autowired
    private InventoryService inventoryService;

    // 在庫情報を表示するページに遷移
    @RequestMapping("/main")
    public String inventory(Model model) {
        List<ProductInventory> productInventory = inventoryService.allProductsWithInventory();
        model.addAttribute("productInventory", productInventory);
        return "HTML/main";
    }

    // idを受け取り、そのidに対応する在庫情報を返す
    @GetMapping("/inventory/{id}")
    public Inventory getInventory(@PathVariable int id) {
        return inventoryService.getInventory(id);
    }

    @RequestMapping("/addInventory")
    public void addInventory(@RequestBody Inventory inventory) {
        inventoryService.addInventory(inventory);
    }

    @RequestMapping("/updateInventory")
    public void updateInventory(@RequestBody Inventory inventory) {
        inventoryService.updateInventory(inventory);
    }

    @RequestMapping("/deleteInventory/{id}")
    public String deleteInventory(@PathVariable int id) {
        inventoryService.deleteInventory(id);
        return "redirect:/main"; 
    }
}