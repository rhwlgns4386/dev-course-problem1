package org.example.article.cli.view;

import org.example.article.cli.dto.request.WriteDto;
import org.example.input.CliInput;

import java.util.Scanner;

public class InputView {


    public static String readCommand(){
        System.out.print("명령어 > ");
        String result = CliInput.readLine();
        System.out.println();
        return result;
    }

    public static WriteDto readWriteDto(){
        System.out.println("제목을 입력해주세요.");
        String title = CliInput.readLine();
        System.out.println("내용을 입력해주세요.");
        String contend = CliInput.readLine();
        return new WriteDto(title, contend);
    }
}
