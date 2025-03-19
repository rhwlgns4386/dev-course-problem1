package org.example.posts.presentation.command;

import org.example.dispatcher.dto.Request;
import org.example.global.exception.InvalidParamException;
import org.example.posts.presentation.controller.PostsController;
import org.example.posts.presentation.dto.request.PostInfoDto;
import org.example.validator.NumberValidator;

import java.util.Arrays;
import java.util.Optional;

public enum Command {
    WRITE("/add") {
        @Override
        public void execute(Request uriRequest, PostsController postsController) {
            String id = uriRequest.getParameter("boardId");
            postsController.write(Command.toValidLong(id),postsController.readInfo());
        }
    },
    LOOKUP("/view") {
        @Override
        public void execute(Request uriRequest, PostsController postsController) {
            String id = uriRequest.getParameter("postId");
            postsController.lookup(Command.toValidLong(id));
        }
    },
    DELETE("/remove") {
        @Override
        public void execute(Request uriRequest, PostsController postsController) {
            String id = uriRequest.getParameter("postId");
            postsController.delete(Command.toValidLong(id));
        }
    },
    UPDATE("/edit") {
        @Override
        public void execute(Request uriRequest, PostsController postsController) {
            String id = uriRequest.getParameter("postId");
            long postId = toValidLong(id);
            postsController.containId(Long.parseLong(id));
            PostInfoDto postInfoDto = postsController.readUpdateInfo(postId);
            postsController.update(postId, postInfoDto);
        }

    };


    private final String path;

    Command(String path) {
        this.path = path;
    }


    public static Optional<Command> findPath(String path){
        return Arrays.stream(values()).filter((value)->path.contains(value.path)).findFirst();
    }

    public abstract void execute(Request uriRequest, PostsController postsController);

    private static InvalidParamException createInvalidParamException() {
        return new InvalidParamException("파라미터가 잘못 되었습니다.");
    }

    private static long toValidLong(String id) {
        if(id == null){
            throw createInvalidParamException();
        }
        NumberValidator.validate(id);
        return Long.parseLong(id);
    }
}
