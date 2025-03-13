package org.example.article.persistance;

public final class LongIdGenerator {

    LongIdGenerator() {
    }

    private Long nextId = 1L;

    Long next() {
        return nextId++;
    }

    void rollback(){
        nextId--;
    }
}
