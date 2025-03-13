package org.example.article.persistance;

import org.example.article.domain.entity.Article;
import org.example.article.domain.service.ArticleRepository;
import org.example.article.domain.service.ArticleService;
import org.example.article.persistance.anotaion.Id;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 이 저장소는 인메모리에 저장하며 시스템을 재부팅시 데이터가 소실됩니다.
 * 또한 멀티스레드환경에서 동시성이 고려되지 않아,
 * 데이터 불일치나 예상치 못한 동작을 할 수 있습니다.
 *
 * @author 고지훈
 * @version 1.0
 * @since 2025-03-13
 */

public class InMemoryArticleRepository implements ArticleRepository {

    private final Map<Long, Article> storedArticles = new HashMap<>();

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Article article) {
        Long id = setId(article);
        storedArticles.put(id, article);
    }

    private static Long setId(Article article) {
        Class<? extends Article> aClass = article.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        try {
            Field field = findIdField(declaredFields);
            field.setAccessible(true);
            field.set(article, KeyGenerator.next());
            return (Long) field.get(article);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Field findIdField(Field[] declaredFields) {
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Id.class)) {
                return declaredField;
            }
        }
        throw new RuntimeException("No id field found");
    }

    @Override
    public List<Article> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public boolean extractById(Long id) {
        return false;
    }
}
