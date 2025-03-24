package org.example.user.persentation.command;

import org.example.dispatcher.CommandType;

import java.util.Arrays;
import java.util.Optional;

public enum Command implements CommandType {

    DETAIL("/detail"),
    SIGNUP("/signup"),
    REMOVE("/remove"),
    EDIT("/edit");

    private final String path;

    Command(String path) {
        this.path = path;
    }

    public static Optional<Command> findPath(String path) {
        return Arrays.stream(values()).filter((value) -> path.contains(value.path)).findFirst();
    }
}
