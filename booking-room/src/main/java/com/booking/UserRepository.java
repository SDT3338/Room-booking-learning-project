package com.booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class UserRepository {

    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean addUser(String name, String email) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, email);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
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
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(long id) {
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

    } catch (SQLException e) {
        e.printStackTrace();
    }

        return null;
    }

    public boolean updateUser(long id, String name, String email) {
    String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, name);
        statement.setString(2, email);
        statement.setLong(3, id);

        int rows = statement.executeUpdate();

        return rows > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
    }

    public boolean deleteUser(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByEmail(String email) {
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
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}