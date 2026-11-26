package com.booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        logger.info("Приложение запущено");
        logger.debug("Проверка");
        logger.warn("Проверка предов");
        logger.error("Проверка ошибок");

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
            System.out.println("Не удалось выполнить операцию");
            logger.error("Ошибка при работе с базой данных", e);
        }
    }
}