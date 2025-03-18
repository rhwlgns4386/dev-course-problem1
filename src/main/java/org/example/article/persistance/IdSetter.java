package org.example.article.persistance;

import org.example.article.domain.entity.Article;
import org.example.article.persistance.anotaion.Id;
import org.example.article.persistance.exception.IdFieldNotFoundException;

import java.lang.reflect.Field;

public class IdSetter {

    IdSetter() {

    }

    void setId(Article article,Long id) throws IdFieldNotFoundException {
        try {
            Field field = findIdField(getFields(article));
            field.setAccessible(true);
            field.set(article, id);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Field[] getFields(Article article) {
        Class<? extends Article> aClass = article.getClass();
        return aClass.getDeclaredFields();
    }

    private Field findIdField(Field[] declaredFields) {
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Id.class)) {
                return declaredField;
            }
        }
        throw new IdFieldNotFoundException("아이디 필드를 찾을 수 없습니다. Entity에 @Id필드를 설정 했는지 확인 하세요");
    }
}
