package org.example.article.cli.view;

import org.example.article.cli.dto.request.IdDto;
import org.example.article.cli.dto.request.WriteDto;
import org.example.input.CliInput;

public class InputView {


    public static String readCommand() {
        System.out.print("명령어 > ");
        String result = CliInput.readLine();
        System.out.println();
        return result;
    }

    public static WriteDto readWriteDto() {
        System.out.println("제목을 입력해주세요.");
        String title = CliInput.readLine();
        System.out.println("내용을 입력해주세요.");
        String contend = CliInput.readLine();
        return new WriteDto(title, contend);
    }

    public static IdDto readId() {
        System.out.print("어떤 게시물을 조회할까요? ");
        String id = CliInput.readLine();
        return new IdDto(id);
    }

    public static IdDto readDeleteId() {
        System.out.print("어떤 게시물을 삭제할까요? ");
        String id = CliInput.readLine();
        return new IdDto(id);
    }

    public static IdDto readUpdateId() {
        System.out.print("어떤 게시물을 수정할까요? ");
        String id = CliInput.readLine();
        System.out.println();
        return new IdDto(id);
    }

    public static WriteDto readUpdate(Long id) {
        System.out.printf("%d번 게시물을 수정합니다.\n",id);
        System.out.print("제목 : ");
        String title = CliInput.readLine();
        System.out.print("내용 : ");
        String contend = CliInput.readLine();
        System.out.println();
        return new WriteDto(title, contend);
    }
}
