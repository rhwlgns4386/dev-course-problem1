package org.example.cli.input;

import java.util.Scanner;

public final class CliInput {
    private static Scanner scanner;

    private CliInput() {}

    public static String readLine(){
        return getInstance().nextLine();
    }

    public static void close(){
        if(scanner != null){
            scanner.close();
            scanner = null;
        }
    }

    private static Scanner getInstance(){
        if(scanner == null){
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
