package org.example.posts.presentation.command.flow;

import org.example.dispatcher.dto.Request;
import org.example.global.ExceptionBoxCommandFlow;
import org.example.posts.presentation.command.PostsCommand;
import org.example.posts.presentation.controller.PostsController;
import org.example.posts.presentation.dto.request.PostInfoDto;

import static org.example.boards.presentation.command.ValidationLongConverter.convert;

public class EditFlow extends ExceptionBoxCommandFlow<PostsCommand> {

    private final PostsController controller;

    public EditFlow(PostsController controller) {
        super(PostsCommand.UPDATE);
        this.controller = controller;
    }

    @Override
    public void doAfter(Request request) {
        String id = request.getParameter("postId");
        long postId = convert(id);
        controller.containId(Long.parseLong(id));
        PostInfoDto postInfoDto = controller.readUpdateInfo(postId);
        controller.update(postId, postInfoDto);
    }
}
