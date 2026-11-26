package com.booking;

public class UserValidator {
    public static void validateName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Неверное введеное имя");
        }
    }
    public static void validateEmail(String email) {
        if(email == null || email.isBlank()) {
            throw new IllegalArgumentException("Неверно введенный email");
        }
        if(!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9_.-]+$")) {
            throw new IllegalArgumentException("Некорректный ввод email");
        }
    }
}
