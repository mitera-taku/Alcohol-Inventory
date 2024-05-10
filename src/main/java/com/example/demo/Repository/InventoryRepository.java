package com.example.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.Domain.Inventory;

import java.util.List;

@Repository
public class InventoryRepository {

    @Autowired
    private NamedParameterJdbcTemplate NamedjdbcTemplate;

    private JdbcTemplate jdbcTemplate;

    public InventoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Inventory> InventoryRowMapper = (rs, i) -> {
        Inventory inventory = new Inventory();
        inventory.setId(rs.getInt("id"));
        inventory.setName(rs.getString("name"));
        inventory.setPrice(rs.getInt("price"));
        inventory.setQuantity(rs.getInt("quantity"));
        return inventory;
    };

    // idを受け取り、そのidに対応する在庫情報を返す
    public Inventory findById(int id) {
        String sql = "SELECT * FROM inventory WHERE product_id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Inventory inventory = NamedjdbcTemplate.queryForObject(sql, param, InventoryRowMapper);
        return inventory;
    }

    // public void save(Inventory Inventory) {
    // if (Inventory == null || Inventory.getProduct() == null) {
    // throw new IllegalArgumentException("ProductInventory or its Product is
    // null");
    // }

    // // ProductInventoryから情報を取得
    // int productId = productInventory.getProduct().getId();
    // int quantity = productInventory.getQuantity();

    // // productsテーブルにデータを挿入
    // int id = productInventory.getProduct().getId();
    // String name = productInventory.getProduct().getName();
    // int price = productInventory.getProduct().getPrice();

    // String ProductSql = "INSERT INTO products (id,name, price) VALUES (:id,:name,
    // :price)";
    // SqlParameterSource ProductParam = new MapSqlParameterSource()
    // .addValue("id", id)
    // .addValue("name", name)
    // .addValue("price", price);
    // jdbcTemplate.update(ProductSql, ProductParam);

    // // inventoryテーブルにデータを挿入
    // String sql = "INSERT INTO inventory (product_id, quantity) VALUES
    // (:product_id, :quantity)";
    // SqlParameterSource param = new MapSqlParameterSource()
    // .addValue("product_id", productId)
    // .addValue("quantity", quantity);
    // // jdbcTemplateを使用してデータを更新
    // jdbcTemplate.update(sql, param);
    // }

    // public void update(Inventory inventory) {
    // String sql = "UPDATE inventory SET quantity = :quantity WHERE product_id =
    // :product_id";
    // SqlParameterSource param = new MapSqlParameterSource().addValue("product_id",
    // inventory.getProduct_id())
    // .addValue("quantity", inventory.getQuantity());
    // quantity.update(sql, param);
    // }

    public boolean deleteByProductId(int productId) {
        String sql = "DELETE FROM products WHERE product_id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("productId", productId);
        int updatedRows = jdbcTemplate.update(sql, param);
        return updatedRows > 0;
    }

    public List<Inventory> findAll() {
        String sql = "SELECT * FROM inventory";
        List<Inventory> inventory = NamedjdbcTemplate.query(sql, InventoryRowMapper);
        return inventory;
    }
}
