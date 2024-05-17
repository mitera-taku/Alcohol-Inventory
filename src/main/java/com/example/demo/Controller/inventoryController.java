package com.example.demo.Controller;

import com.example.demo.Domain.Inventory;
import com.example.demo.Service.InventoryService;

import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        List<Inventory> Inventory = inventoryService.allInventory();
        model.addAttribute("Inventory", Inventory);
        return "HTML/main";
    }

    // idを受け取り、そのidに対応する在庫情報を返す
    @GetMapping("/inventory/{id}")
    public Inventory getInventory(@PathVariable int id) {
        return inventoryService.getInventory(id);
    }

    @RequestMapping("add")
    public String add() {
        return "HTML/add";
    }

    // 在庫情報を追加
    @RequestMapping("/addInventory")
    public String addInventory(Inventory Inventory) {
        inventoryService.addInventory(Inventory);
        return "redirect:/main";
    }

    @RequestMapping("/editInventory/{id}")
    public String editInventory(int id, Model model) {
        Inventory inventory = inventoryService.getInventory(id);
        model.addAttribute("inventory", inventory);
        return "HTML/update";
    }

    @RequestMapping("/updateInventory")
    public String updateInventory(Inventory Inventory) {
        inventoryService.updateInventory(Inventory);
        return "redirect:/main";
    }

    @RequestMapping("/deleteInventory/{id}")
    public String deleteInventory(@PathVariable int id) {
        inventoryService.checkInventory(id);
        return "redirect:/main";
    }
}