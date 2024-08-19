package com.example.demo.Repository;

import com.example.demo.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // RowMapperを使用してResultSetからUserオブジェクトにマッピング
    private static final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    };

    // ユーザーを登録するメソッド
    public void saveUser(User user) {
        String sql = "INSERT INTO users (username, email, password) VALUES (:username, :email, :password)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", user.getUsername());
        params.addValue("email", user.getEmail());
        params.addValue("password", user.getPassword());

        namedParameterJdbcTemplate.update(sql, params);
    }

    // ユーザーをIDで取得するメソッド
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return namedParameterJdbcTemplate.queryForObject(sql, params, userRowMapper);
    }
}
