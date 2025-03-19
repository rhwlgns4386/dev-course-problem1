package org.example.posts.presentation.command;

import java.util.Arrays;
import java.util.Optional;

public enum PostsCommand {
    WRITE("/add"),
    LOOKUP("/view"),
    DELETE("/remove"),
    UPDATE("/edit");


    private final String path;

    PostsCommand(String path) {
        this.path = path;
    }


    public static Optional<PostsCommand> findPath(String path){
        return Arrays.stream(values()).filter((value)->path.contains(value.path)).findFirst();
    }

}
