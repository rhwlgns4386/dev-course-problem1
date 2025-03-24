package org.example.posts.presentation.command;

import org.example.cli.CommandFlow;
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

public class PostRequestHandlerAdaptor extends BaseRequestHandler<PostsCommand> {

    private final String PREFIX = "/posts";

    public PostRequestHandlerAdaptor(PostsController postsController, ExceptionHandler exceptionHandler) {
        super(exceptionHandler, commandFlows(postsController));
    }

    private static List<CommandFlow<PostsCommand>> commandFlows(PostsController postsController) {
        return List.of(new AddFlow(postsController), new EditFlow(postsController), new RemoveFlow(postsController), new ViewFlow(postsController));
    }

    @Override
    public boolean support(Request request) {
        String url = request.getUrl();
        return url.startsWith(PREFIX);
    }

    private String extractCommandString(Request commandInput) {
        String value = commandInput.getUrl();
        return value.substring(PREFIX.length());
    }

    @Override
    public PostsCommand extractCommandKey(Request request) {
        return PostsCommand.findPath(extractCommandString(request)).orElseThrow(() -> new CommandNotFoundException("존재하지 않는 명령어 입니다."));
    }
}
