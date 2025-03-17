package org.example.article.cli.view;

import org.example.article.cli.dto.request.ArticleInfoDto;
import org.example.input.CliInput;

public class InputView {


    public static ArticleInfoDto readArticleInfo() {
        System.out.print("제목 : ");
        String title = CliInput.readLine();
        System.out.print("내용 : ");
        String contend = CliInput.readLine();
        return new ArticleInfoDto(title, contend);
    }

    public static String readId(String command) {
        System.out.printf("어떤 게시물을 %s할까요? ",command);
        String id = CliInput.readLine();
        return id;
    }

    public static ArticleInfoDto readUpdateInfo(Long id) {
        System.out.printf("%d번 게시물을 수정합니다.\n",id);
        return readArticleInfo();
    }
}
