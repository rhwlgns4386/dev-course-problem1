package org.example.boards.presentation.command;

import org.example.boards.presentation.controller.BoardController;
import org.example.dispatcher.dto.Request;
import org.example.global.exception.InvalidParamException;
import org.example.validator.NumberValidator;

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
