package com.example.tpo_03;

public class IdGenerator {
    private static long id = 1;

    public static synchronized long generateId() {
        return id++;
    }
}
