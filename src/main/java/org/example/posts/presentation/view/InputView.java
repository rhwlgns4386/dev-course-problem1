package org.example.posts.presentation.view;

import org.example.posts.presentation.dto.request.PostInfoDto;
import org.example.input.CliInput;

public class InputView {


    public static PostInfoDto readArticleInfo() {
        System.out.print("제목 : ");
        String title = CliInput.readLine();
        System.out.print("내용 : ");
        String contend = CliInput.readLine();
        return new PostInfoDto(title, contend);
    }

    public static PostInfoDto readUpdateInfo(Long id) {
        System.out.printf("%d번 게시물을 수정합니다.\n",id);
        return readArticleInfo();
    }
}
