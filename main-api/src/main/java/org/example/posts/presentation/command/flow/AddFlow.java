package org.example.posts.presentation.command.flow;

import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;
import org.example.global.ExceptionBoxCommandFlow;
import org.example.global.exception.InvalidParamException;
import org.example.posts.presentation.command.PostsCommand;
import org.example.posts.presentation.controller.PostsController;

import static org.example.boards.presentation.command.ValidationLongConverter.convert;

public class AddFlow extends ExceptionBoxCommandFlow<PostsCommand> {

    private final  PostsController controller;

    public AddFlow(PostsController controller) {
        super(PostsCommand.WRITE);
        this.controller = controller;
    }

    @Override
    protected void doAfter(Request request) {
        String id = request.getParameter("boardId");
        controller.write(convert(id),controller.readInfo(),request);
    }
}
