package org.example.posts.presentation.command.flow;

import org.example.dispatcher.dto.Request;
import org.example.global.ExceptionBoxCommandFlow;
import org.example.posts.presentation.command.PostsCommand;
import org.example.posts.presentation.controller.PostsController;

import static org.example.boards.presentation.command.ValidationLongConverter.convert;

public class RemoveFlow extends ExceptionBoxCommandFlow<PostsCommand> {

    private final PostsController controller;

    public RemoveFlow(PostsController controller) {
        super(PostsCommand.DELETE);
        this.controller = controller;
    }

    @Override
    public void doAfter(Request request) {
        String id = request.getParameter("postId");
        controller.delete(convert(id));
    }
}
