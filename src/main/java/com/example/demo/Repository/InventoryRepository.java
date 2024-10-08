package com.example.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.Domain.inventory;
import com.example.demo.Form.Inventory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Repository
public class InventoryRepository {

    @Autowired
    private NamedParameterJdbcTemplate NamedjdbcTemplate;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

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
        String sql = "SELECT * FROM inventory WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Inventory inventory = NamedjdbcTemplate.queryForObject(sql, param, InventoryRowMapper);
        return inventory;
    }

    public void save(inventory Inventory) {
        int id = generateNewId();
        Inventory.setId(id);
        String sql = "INSERT INTO inventory (id, name, price, quantity) VALUES (:id, :name, :price, :quantity)";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", Inventory.getId())
                .addValue("name", Inventory.getName())
                .addValue("price", Inventory.getPrice())
                .addValue("quantity", Inventory.getQuantity());
        NamedjdbcTemplate.update(sql, param);
    }

    public void update(inventory inventory) {
        String sql = """
                UPDATE
                inventory
                SET
                name= :name,
                price= :price,
                quantity= :quantity
                WHERE
                id= :id
                """;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", inventory.getId())
                .addValue("name", inventory.getName())
                .addValue("price", inventory.getPrice())
                .addValue("quantity", inventory.getQuantity());
        NamedjdbcTemplate.update(sql, param);
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM inventory WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        int updatedRows = NamedjdbcTemplate.update(sql, param);
        return updatedRows > 0;
    }

    public List<Inventory> findAll() {
        String sql = "SELECT * FROM inventory ORDER BY quantity ASC, price ASC";
        List<Inventory> inventory = NamedjdbcTemplate.query(sql, InventoryRowMapper);
        return inventory;
    }

    // 新しいidを生成
    public int generateNewId() {
        String sqlCount = "SELECT COUNT(*) FROM inventory";
        Integer countObj = jdbcTemplate.queryForObject(sqlCount, (rs, rowNum) -> rs.getInt(1));
        int count = (countObj != null) ? countObj : 0;

        String sqlCheck = "SELECT COUNT(*) FROM inventory WHERE id = ?";
        Integer existObj;
        while ((existObj = jdbcTemplate.queryForObject(sqlCheck, (rs, rowNum) -> rs.getInt(1), count + 1)) != null
                && existObj > 0) {
            count++;
        }

        return count + 1;
    }

    // 商品名で部分一致検索
    public List<Inventory> findByNameContaining(String name) {
        // JPQLクエリをエンティティ名で実行
        String queryStr = "SELECT i FROM inventory i WHERE i.name LIKE :name";
        TypedQuery<Inventory> query = entityManager.createQuery(queryStr, Inventory.class);
        // 部分一致検索用
        query.setParameter("name", "%" + name + "%");  
        return query.getResultList();
    }
        
}
