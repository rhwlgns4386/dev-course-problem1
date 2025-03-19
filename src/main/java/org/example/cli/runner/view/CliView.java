package org.example.cli.runner.view;

import org.example.input.CliInput;

public class CliView {

    public static String readCommand() {
        System.out.print("a ");
        return CliInput.readLine();
    }
}
