package org.example.posts.presentation;

import org.example.dispatcher.dto.Request;
import org.example.global.BaseRequestHandler;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.ExceptionHandler;
import org.example.posts.presentation.command.CommandUrl;
import org.example.posts.presentation.controller.PostsController;

public class PostRequestHandlerAdaptor extends BaseRequestHandler {

    private final String PREFIX = "/posts";
    private final PostsController postsController;

    public PostRequestHandlerAdaptor(PostsController postsController, ExceptionHandler exceptionHandler) {
        super(exceptionHandler);
        this.postsController = postsController;
    }

    @Override
    public boolean support(Request request) {
        String url = request.getUrl();
        return url.startsWith(PREFIX);
    }

    @Override
    public void execute(Request commandInput) {
        CommandUrl command = CommandUrl.findPath(extractCommandString(commandInput)).orElseThrow(() -> new CommandNotFoundException("존재하지 않는 명령어 입니다."));
        command.execute(commandInput, postsController);
    }

    private String extractCommandString(Request commandInput) {
        String value = commandInput.getUrl();
        return value.substring(PREFIX.length());
    }
}
