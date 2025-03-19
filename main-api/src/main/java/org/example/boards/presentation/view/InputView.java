package org.example.boards.presentation.view;

import org.example.cli.input.CliInput;

public final class InputView {

    private InputView() {
    }

    public static String readTitle(){
        System.out.print("제목 : ");
        return CliInput.readLine();
    }
}
