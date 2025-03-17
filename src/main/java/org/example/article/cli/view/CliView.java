package org.example.article.cli.view;

import org.example.input.CliInput;

public class CliView {

    public static String readCommand() {
        System.out.print("명령어 > ");
        return CliInput.readLine();
    }
}
