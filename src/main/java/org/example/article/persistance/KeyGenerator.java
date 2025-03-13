package org.example.article.persistance;

public class KeyGenerator {

    private static Long nextId = 1L;

    public static Long next() {
        return nextId++;
    }
}
