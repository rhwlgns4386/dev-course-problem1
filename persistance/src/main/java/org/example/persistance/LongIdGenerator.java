package org.example.persistance;

public final class LongIdGenerator {

    private static final long DEFAULT_VALUE = 1L;

    LongIdGenerator() {
    }

    private Long nextId = DEFAULT_VALUE;

    Long next() {
        return nextId++;
    }

    void rollback(){
        nextId--;
    }

    public void clear() {
        nextId = DEFAULT_VALUE;
    }
}
