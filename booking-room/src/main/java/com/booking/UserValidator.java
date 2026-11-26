package com.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserValidator {
    private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);
    public static void validateName(String name) {
        if(name == null || name.isBlank()) {
            logger.warn("Получено пустое имя пользователя.");
            throw new IllegalArgumentException("Неверное введеное имя");
        }
    }
    public static void validateEmail(String email) {
        if(email == null || email.isBlank()) {
            logger.warn("Получен пустой email пользователя.");
            throw new IllegalArgumentException("Неверно введенный email");
        }
        if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9_.-]+$")) {
            logger.warn("Получен некорректный email пользователя");
            throw new IllegalArgumentException("Некорректный ввод email");
        }
    }
}
