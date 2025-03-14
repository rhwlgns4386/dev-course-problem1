package org.example.article.cli.command;

import java.util.Optional;

public enum Command {
    EXIT("종료"),
    ALL("목록"),
    WRITE("작성"),
    LOOKUP("조회");


    private final String krCommand;
    Command(String krCommand) {
        this.krCommand = krCommand;
    }

    public static Optional<Command> findByName(String commandName) {
        for (Command command : Command.values()) {
            if (command.name().equalsIgnoreCase(commandName) || command.krCommand.equals(commandName)) {
                return Optional.of(command);
            }
        }
        return Optional.empty();
    }
}
