package org.example.auth.persentation.command;

import org.example.dispatcher.CommandType;

import java.util.Arrays;
import java.util.Optional;

public enum Command implements CommandType {

    SIGNIN("/signin"),
    SIGNOUT("/signout");

    private final String path;

    Command(String path) {
        this.path = path;
    }

    public static Optional<Command> findPath(String path) {
        return Arrays.stream(values()).filter((value) -> path.contains(value.path)).findFirst();
    }
}
