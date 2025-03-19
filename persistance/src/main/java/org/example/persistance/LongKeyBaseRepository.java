package org.example.persistance;

import org.example.persistance.exception.IdFieldNotFoundException;

import java.util.*;

/**
 * 이 저장소는 인메모리에 저장하며 시스템을 재부팅시 데이터가 소실됩니다.
 * 또한 멀티스레드환경에서 동시성이 고려되지 않아,
 * 데이터 불일치나 예상치 못한 동작을 할 수 있습니다.
 *
 * @author 고지훈
 * @version 1.0
 * @since 2025-03-13
 */
public class LongKeyBaseRepository<E> implements BaseRepository<Long,E>{
    protected final Map<Long,E> store = new HashMap<>();
    private final LongIdGenerator idGenerator = new LongIdGenerator();
    private final IdSetter idSetter = new IdSetter();

    @Override
    public Optional<E> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public E save(E entity) throws IdFieldNotFoundException {
        try {
            Long id = idGenerator.next();
            idSetter.setId(entity,id);
            store.put(id, entity);
            return entity;
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
