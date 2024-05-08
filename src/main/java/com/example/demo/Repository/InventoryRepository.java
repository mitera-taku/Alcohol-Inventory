package com.example.demo.Repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.Domain.Inventory;

@Repository
public class InventoryRepository {

    private NamedParameterJdbcTemplate quantity;

    private static final RowMapper<Inventory> InventoryRowMapper = (rs, i) -> {
        Inventory inventory = new Inventory();
        inventory.setProduct_id(rs.getInt("product_id"));
        inventory.setQuantity(rs.getInt("quantity"));
        return inventory;
    };

    // idを受け取り、そのidに対応する在庫情報を返す
    public Inventory findById(int id) {
        String sql = "SELECT * FROM inventory WHERE product_id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Inventory inventory = quantity.queryForObject(sql, param, InventoryRowMapper);
        return inventory;
    }

    public Inventory save(Inventory inventory) {
        String sql = "INSERT INTO inventory (product_id, quantity) VALUES (:product_id, :quantity)";
        return null;
    }

    public Inventory update(Inventory inventory) {
        String sql = "UPDATE inventory SET quantity = :quantity WHERE product_id = :product_id";
        return null;
    }

    public Inventory deleteById(int id) {
        String sql = "DELETE FROM inventory WHERE product_id = :id";
        return null;
    }
}
