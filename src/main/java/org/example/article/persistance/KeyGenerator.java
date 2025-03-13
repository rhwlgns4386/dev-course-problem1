package org.example.article.persistance;

public class KeyGenerator {

    private Long nextId = 1L;

    public Long next() {
        return nextId++;
    }
}
