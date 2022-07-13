package ru.netology.house;

public class NotRegisteredException extends RuntimeException {
    public NotRegisteredException(String s) {
        super(s);
    }
}
