package org.example.article.cli.view;

import org.example.input.CliInput;

import java.util.Scanner;

public class InputView {


    public static String readCommand(){
        System.out.print("명령어 > ");
        String result = CliInput.readLine();
        System.out.println();
        return result;
    }
}
