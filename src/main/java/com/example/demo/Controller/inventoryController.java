package com.example.demo.Controller;

import com.example.demo.Domain.inventory;
import com.example.demo.Form.Inventory;
import com.example.demo.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("")
public class inventoryController {

    @Autowired
    private InventoryService inventoryService;

    // 在庫情報を表示するページに遷移
    @RequestMapping("/main")
    public String inventory(Model model) {
        List<Inventory> inventoryList = inventoryService.allInventory();
        model.addAttribute("Inventory", inventoryList);
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
    public String addInventory(inventory inventory) {
        inventoryService.addInventory(inventory);
        return "redirect:/main";
    }

    @RequestMapping("/editInventory/{id}")
    public String editInventory(@PathVariable int id, Model model) {
        Inventory inventory = inventoryService.getInventory(id);
        model.addAttribute("inventory", inventory);
        return "HTML/update";
    }

    @RequestMapping("/updateInventory")
    public String updateInventory(inventory inventory) {
        inventoryService.updateInventory(inventory);
        return "redirect:/main";
    }

    @RequestMapping("/deleteInventory/{id}")
    public String deleteInventory(@PathVariable int id) {
        inventoryService.checkInventory(id);
        return "redirect:/main";
    }
    // 検索機能を追加
    @GetMapping("/search")
    public String searchProducts(@RequestParam("name") String name, Model model) {
        try {
            // サービス層を使って商品を検索
            List<Inventory> products;

            if (name == null || name.isEmpty()) {
                // 商品名が入力されていない場合は、全商品を表示
                products = inventoryService.allInventory();
            } else {
                // 商品名に部分一致する商品を検索
                products = inventoryService.searchProductsByName(name);
            }

               // 検索結果が空の場合にメッセージを設定
        if (products.isEmpty()) {
            model.addAttribute("message", "商品が見当たりません");
        } else {
            model.addAttribute("Inventory", products);
        }
            // 結果を表示するテンプレートを返す（"main.html"）
            return "HTML/main";  // 正しいHTMLテンプレート名に合わせて修正
        } catch (Exception e) {
            // エラーログを出力し、エラーページを返す
            e.printStackTrace();
            return "HTML/error";  // エラーページへの遷移
        }
    }

}
