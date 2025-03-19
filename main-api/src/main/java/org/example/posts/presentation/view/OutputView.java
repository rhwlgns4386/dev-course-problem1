package org.example.posts.presentation.view;

import org.example.posts.domain.entity.Post;

import java.time.format.DateTimeFormatter;

public final class OutputView {

    private OutputView(){}

    private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static void render(Post post) {
        System.out.printf("%s번 게시글\n", post.id());
        System.out.printf("작성일 : %s", post.createdDate().format(pattern));
        System.out.printf("수정일 : %s", post.updatedDate().format(pattern));
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
