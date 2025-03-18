package org.example.article.persistance;

import org.example.article.domain.entity.Article;
import org.example.article.domain.service.ArticleRepository;
import org.example.article.persistance.exception.IdFieldNotFoundException;
import org.example.persistance.IdSetter;
import org.example.persistance.LongIdGenerator;
import org.example.persistance.LongKeyBaseRepository;

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

public class InMemoryArticleRepository extends LongKeyBaseRepository<Article> implements ArticleRepository {

}
