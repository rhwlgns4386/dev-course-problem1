package org.example.posts.presentation.view;

import org.example.posts.domain.entity.Post;
import org.example.posts.domain.entity.Posts;

public final class OutputView {

    private OutputView(){}

    public static void renderExit() {
        System.out.println("프로그램이 종료됩니다.");
    }

    public static void renderList(Posts posts) {
        System.out.printf("총 게시글은 %s개 작성되어있습니다.\n\n", posts.size());

        for(Post post : posts.articles()){
           render(post);
        }
    }

    public static void render(Post post) {
        System.out.printf("%s번 게시글\n", post.id());
        System.out.printf("제목 : %s\n", post.title());
        System.out.printf("내용 : %s\n", post.content());
    }

    public static void renderDeleted(Long id) {
        System.out.printf("%s번 게시물이 성공적으로 삭제되었습니다!\n",id);
    }

    public static void renderUpdate(Long id) {
        System.out.printf("%s번 게시물이 성공적으로 수정되었습니다!\n",id);
    }
}
