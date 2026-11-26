package com.booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRepository {

    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean addUser(String name, String email) throws SQLException {
        UserValidator.validateName(name);
        UserValidator.validateEmail(email);
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, email);

            int rows = statement.executeUpdate();

            logger.info("Добавление в базу данных произошло успешно: {}", email);

            return rows > 0;
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try(PreparedStatement statement = connection.prepareStatement(sql); 
            ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                User user = new User(id, name, email);
                users.add(user);
            }
        } 
        return users;
    }

    public User getUserById(long id) throws SQLException {
    String sql = "SELECT * FROM users WHERE id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setLong(1, id);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                long userId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                return new User(userId, name, email);
            }
        }

    } 
        return null;
    }

    public boolean updateUser(long id, String name, String email) throws SQLException {
        UserValidator.validateName(name);
        UserValidator.validateEmail(email);
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setLong(3, id);

            int rows = statement.executeUpdate();

            return rows > 0;

        } 
    }

    public boolean deleteUser(long id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int row = statement.executeUpdate();
            return row > 0;
        } 
    }

    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String email_user = resultSet.getString("email");

                    return new User(id, name, email_user);
                }
            }
        } 
        return null;
    }
}