package org.example.posts.presentation.command;

import org.example.cli.CommandFlowFinder;
import org.example.dispatcher.dto.Request;
import org.example.global.BaseRequestHandler;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.ExceptionHandler;
import org.example.posts.presentation.command.flow.AddFlow;
import org.example.posts.presentation.command.flow.EditFlow;
import org.example.posts.presentation.command.flow.RemoveFlow;
import org.example.posts.presentation.command.flow.ViewFlow;
import org.example.posts.presentation.controller.PostsController;

import java.util.List;

public class PostRequestHandlerAdaptor extends BaseRequestHandler {

    private final String PREFIX = "/posts";
    private final PostsController postsController;
    private final CommandFlowFinder<PostsCommand, PostsController> finder;

    public PostRequestHandlerAdaptor(PostsController postsController, ExceptionHandler exceptionHandler) {
        super(exceptionHandler);
        this.postsController = postsController;
        this.finder = new CommandFlowFinder<>(List.of(new AddFlow(), new EditFlow(), new RemoveFlow(), new ViewFlow()));
    }

    @Override
    public boolean support(Request request) {
        String url = request.getUrl();
        return url.startsWith(PREFIX);
    }

    @Override
    public void execute(Request commandInput) {
        PostsCommand postsCommand = PostsCommand.findPath(extractCommandString(commandInput)).orElseThrow(() -> new CommandNotFoundException("존재하지 않는 명령어 입니다."));
        finder.find(postsCommand).ifPresent(commandFlow -> commandFlow.execute(postsController, commandInput));
    }

    private String extractCommandString(Request commandInput) {
        String value = commandInput.getUrl();
        return value.substring(PREFIX.length());
    }
}
