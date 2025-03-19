package org.example.cli.test;

import org.example.cli.input.CliInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class ApplicationTest {

    private PrintStream stdout;
    private OutputStream snapShot;

    @BeforeEach
    protected void init(){
        stdout = System.out;
        snapShot = new ByteArrayOutputStream();
        System.setOut(new  PrintStream(snapShot));
    }

    @AfterEach
    protected void after(){
        System.setOut(stdout);
        System.out.println(snapShot);
    }

    protected String out(){
        return snapShot.toString();
    }

    protected void in(String... values){
        String input = String.join("\n", values);
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    protected void run(Runnable runnable){
        try {
            runnable.run();
        }finally {
            CliInput.close();
        }
    }
}
