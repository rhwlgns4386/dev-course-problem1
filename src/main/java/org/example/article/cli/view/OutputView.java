package org.example.article.cli.view;

import org.example.article.domain.entity.Article;
import org.example.article.domain.entity.Articles;

public final class OutputView {

    private OutputView(){}

    public static void renderExit() {
        System.out.println("프로그램이 종료됩니다.");
    }

    public static void renderList(Articles articles) {
        System.out.printf("총 게시글은 %s개 작성되어있습니다.\n\n",articles.size());

        for(Article article : articles.articles()){
            System.out.printf("%s번 게시글\n",article.id());
            System.out.printf("제목 : %s\n",article.title());
            System.out.printf("내용 : %s\n",article.content());
        }
    }
}
