package org.example.persistance;

import org.example.article.persistance.exception.IdFieldNotFoundException;

import java.util.*;

public class LongKeyBaseRepository<E> implements BaseRepository<Long,E>{
    protected final Map<Long,E> store = new HashMap<>();
    private final LongIdGenerator idGenerator = new LongIdGenerator();
    private final IdSetter idSetter = new IdSetter();

    @Override
    public Optional<E> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void save(E article) throws IdFieldNotFoundException {
        try {
            Long id = idGenerator.next();
            idSetter.setId(article,id);
            store.put(id, article);
        }catch (IdFieldNotFoundException e){
            idGenerator.rollback();
            throw e;
        }
    }

    @Override
    public List<E> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public boolean extractById(Long id) {
        return store.containsKey(id);
    }

    @Override
    public void clear() {
        this.store.clear();
        this.idGenerator.clear();
    }
}
