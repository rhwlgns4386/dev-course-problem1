package org.example.cli.test;

import org.example.cli.runner.ApplicationStateHolder;
import org.example.cli.runner.CountBaseApplicationState;
import org.junit.jupiter.api.AfterEach;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CliApplicationTest extends ApplicationTest{

    protected void in(Consumer<InputBuilder> input){
        InputBuilder inputBuilder = new InputBuilder();
        input.accept(inputBuilder);
        in(inputBuilder.build());
    }

    @AfterEach
    protected void after(){
        ApplicationStateHolder.stop();
        super.after();
    }

    public static class InputBuilder{
        private final List<String> input = new ArrayList<>();
        private int commandCount = 0;
        public InputBuilder command(String command){
            commandCount++;
            return input(command);
        }

        public InputBuilder input(String... input){
            this.input.addAll(List.of(input));
            return this;
        }

        private String[] build(){
            ApplicationStateHolder.setApplicationState(new CountBaseApplicationState(commandCount));
            return input.toArray(new String[0]);
        }
    }
}
