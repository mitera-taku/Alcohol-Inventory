package com.example.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.Domain.Inventory;
import com.example.demo.Domain.ProductInventory;
import com.example.demo.Domain.Products;

import java.util.List;

@Repository
public class InventoryRepository {

    @Autowired
    private NamedParameterJdbcTemplate quantity;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final RowMapper<Inventory> InventoryRowMapper = (rs, i) -> {
        Inventory inventory = new Inventory();
        inventory.setProduct_id(rs.getInt("product_id"));
        inventory.setQuantity(rs.getInt("quantity"));
        return inventory;
    };

    private static final RowMapper<Products> ProductsRowMapper = (rs, i) -> {
        Products products = new Products();
        products.setId(rs.getInt("product_id"));
        products.setName(rs.getString("name"));
        products.setPrice(rs.getString("price"));
        return products;
    };

    private static final RowMapper<ProductInventory> ProductInventoryRowMapper = (rs, i) -> {
        ProductInventory productInventory = new ProductInventory();

        // Map product fields
        Products product = new Products();
        product.setId(rs.getInt("product_id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getString("price"));
        productInventory.setProduct(product);

        // Map inventory fields
        Inventory inventory = new Inventory();
        inventory.setProduct_id(rs.getInt("product_id"));
        inventory.setQuantity(rs.getInt("quantity"));
        productInventory.setInventory(inventory);

        return productInventory;
    };

    public List<ProductInventory> getAllProductsWithInventory() {
        String sql = "SELECT * FROM products INNER JOIN inventory ON products.id = inventory.product_id";
        List<ProductInventory> ProductInventoryList = jdbcTemplate.query(sql, ProductInventoryRowMapper);
        return ProductInventoryList;
    }

    // idを受け取り、そのidに対応する在庫情報を返す
    public Inventory findById(int id) {
        String sql = "SELECT * FROM inventory WHERE product_id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Inventory inventory = quantity.queryForObject(sql, param, InventoryRowMapper);
        return inventory;
    }

    public void save(ProductInventory productInventory) {
        // ProductInventoryから情報を取得
        int productId = productInventory.getProduct().getId();
        int quantity = productInventory.getQuantity();

        // productsテーブルにデータを挿入

        String ProductSql = "INSERT INTO products (id,name, price) VALUES (:id,:name, :price)";
        SqlParameterSource ProductParam = new MapSqlParameterSource()
                .addValue("id", productInventory.getProduct().getId())
                .addValue("name", productInventory.getProduct().getName())
                .addValue("price", productInventory.getProduct().getPrice());
        jdbcTemplate.update(ProductSql, ProductParam);

        // inventoryテーブルにデータを挿入
        String sql = "INSERT INTO inventory (product_id, quantity) VALUES (:product_id, :quantity)";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("product_id", productId)
                .addValue("quantity", quantity);
        jdbcTemplate.update(sql, param); // jdbcTemplateを使用してデータを更新
    }

    public void update(Inventory inventory) {
        String sql = "UPDATE inventory SET quantity = :quantity WHERE product_id = :product_id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("product_id", inventory.getProduct_id())
                .addValue("quantity", inventory.getQuantity());
        quantity.update(sql, param);
    }

    public Inventory deleteById(int id) {
        String sql = "DELETE FROM inventory WHERE product_id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Inventory inventory = quantity.queryForObject(sql, param, InventoryRowMapper);
        return inventory;
    }

    public List<Products> findAll() {
        String sql = "SELECT * FROM inventory";
        List<Products> inventory = quantity.query(sql, ProductsRowMapper);
        return inventory;
    }
}
