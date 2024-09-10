package com.example.demo.Repository;

import com.example.demo.Domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // コンストラクタ
    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private static final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    };

    public void saveUser(User user) {
        String sql = "INSERT INTO users (username, email, password) VALUES (:username, :email, :password)";
        // パスワードのハッシュ化はリポジトリ外で処理するように変更
        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("username", user.getUsername())
            .addValue("email", user.getEmail())
            .addValue("password", user.getPassword());
        namedParameterJdbcTemplate.update(sql, params);
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, params, userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null; // ユーザーが見つからない場合はnullを返す
        }
    }
}
