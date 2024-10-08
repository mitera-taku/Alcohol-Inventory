package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Domain.inventory;
import com.example.demo.Form.Inventory;
import com.example.demo.Repository.InventoryRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory getInventory(int id) {
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
        Inventory inventory = inventoryRepository.findById(id);
        if (inventory != null) {
            return inventoryRepository.deleteById(id);
        }
        return false; // Add this line to return false if the inventory is null
    }

    public List<Inventory> allInventory() {
        List<Inventory> inventory = inventoryRepository.findAll();
        return inventory;
    }

       // 商品名で検索するメソッド
    public List<Inventory> searchProductsByName(String name) {
        return inventoryRepository.findByNameContaining(name);
    }

    public void exportInventoryToCSV(String filePath) throws IOException {
        // リポジトリから在庫データを取得
        List<Inventory> inventoryList = inventoryRepository.findAll();
        System.out.println(inventoryList);
        System.out.println("Inventory List Size: " + inventoryList.size());
        FileWriter csvWriter = new FileWriter(filePath);

        // ヘッダーを書き込み
        csvWriter.append("Product ID, Name, Quantity, Price\n");

        // 各商品のデータを書き込み
        for (Inventory inventory : inventoryList) {
            csvWriter.append(String.valueOf(inventory.getId()))   // getId()の戻り値がIntegerの場合
                     .append(",")
                     .append(inventory.getName() != null ? inventory.getName() : "N/A")
                     .append(",")
                     .append(String.valueOf(inventory.getQuantity()))  // intをStringに変換
                     .append(",")
                     .append(String.valueOf(inventory.getPrice()))     // int/doubleをStringに変換
                     .append("\n");
        }

        // 書き込み後にファイルを閉じる
        csvWriter.flush();
        csvWriter.close();
    }
}
