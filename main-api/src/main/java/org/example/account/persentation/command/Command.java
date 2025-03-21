package org.example.account.persentation.command;

import java.util.Arrays;
import java.util.Optional;

public enum Command {

    DETAIL("/detail"),
    SIGNUP("/signup"),
    REMOVE("/remove"),
    EDIT("/edit"),
    SIGNIN("/signin"),
    SIGNOUT("/signout"),;

    private final String path;

    Command(String path) {
        this.path = path;
    }

    public static Optional<Command> findPath(String path) {
        return Arrays.stream(values()).filter((value) -> path.contains(value.path)).findFirst();
    }
}
