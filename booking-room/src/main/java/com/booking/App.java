package com.booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class App {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        try (Connection connection =
                     DriverManager.getConnection(url, username, password)) {

            System.out.println("Подключение успешно!");

            UserRepository repository = new UserRepository(connection);

            boolean added = repository.addUser("Alex", "alex@gmail.com");
            System.out.println("Добавление: " + added);
        } catch (SQLException e) {
            System.out.println("Ошибка работы с базой данных");
            e.printStackTrace();
        }
    }
}