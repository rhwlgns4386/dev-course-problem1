package org.example.boards.presentation.command;

import java.util.Arrays;
import java.util.Optional;

public enum BoardCommand {
    ADD("/add"), EDIT("/edit"), REMOVE("/remove"), VIEW("/view");

    private String path;

    BoardCommand(String path) {
        this.path = path;
    }

    public static Optional<BoardCommand> findPath(String path) {
        return Arrays.stream(values()).filter((value) -> path.contains(value.path)).findFirst();
    }
}
