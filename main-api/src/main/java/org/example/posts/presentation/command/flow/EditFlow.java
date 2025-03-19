package org.example.posts.presentation.command.flow;

import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.global.exception.InvalidParamException;
import org.example.posts.presentation.command.PostsCommand;
import org.example.posts.presentation.controller.PostsController;
import org.example.posts.presentation.dto.request.PostInfoDto;

import static org.example.boards.presentation.command.ValidationLongConverter.convert;

public class EditFlow extends CommandFlow<PostsCommand, PostsController> {

    public EditFlow() {
        super(PostsCommand.UPDATE);
    }

    @Override
    public void execute(PostsController controller, Request request) {
        String id = request.getParameter("postId");
        try{
            long postId = convert(id);
            controller.containId(Long.parseLong(id));
            PostInfoDto postInfoDto = controller.readUpdateInfo(postId);
            controller.update(postId, postInfoDto);
        }catch (NullPointerException e){
            throw new InvalidParamException("파라미터가 잘못 되었습니다.");
        }
    }
}
