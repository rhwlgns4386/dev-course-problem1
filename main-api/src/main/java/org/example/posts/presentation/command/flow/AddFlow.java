package org.example.posts.presentation.command.flow;

import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.global.exception.InvalidParamException;
import org.example.posts.presentation.command.PostsCommand;
import org.example.posts.presentation.controller.PostsController;

import static org.example.boards.presentation.command.ValidationLongConverter.convert;

public class AddFlow extends CommandFlow<PostsCommand, PostsController> {

    public AddFlow() {
        super(PostsCommand.WRITE);
    }

    @Override
    public void execute(PostsController controller, Request request) {
        String id = request.getParameter("boardId");
        try{
            controller.write(convert(id),controller.readInfo());
        }catch (NullPointerException e){
            throw new InvalidParamException("파라미터가 잘못 되었습니다.");
        }
    }
}
