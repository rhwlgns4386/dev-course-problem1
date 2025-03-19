package org.example.posts.presentation.command;

import org.example.dispatcher.dto.Request;
import org.example.posts.presentation.controller.PostsController;
import org.example.posts.presentation.dto.request.IdDto;
import org.example.posts.presentation.dto.request.PostInfoDto;

import java.util.Arrays;
import java.util.Optional;

public enum CommandUrl {
    WRITE("/add") {
        @Override
        public void execute(Request uriRequest, PostsController postsController) {
            String id = uriRequest.getParameter("bordId");
            postsController.write(new IdDto(id),postsController.readInfo());
        }
    },
    LOOKUP("/view") {
        @Override
        public void execute(Request uriRequest, PostsController postsController) {
            String id = uriRequest.getParameter("postId");
            postsController.lookup(new IdDto(id));
        }
    },
    DELETE("/remove") {
        @Override
        public void execute(Request uriRequest, PostsController postsController) {
            String id = uriRequest.getParameter("postId");
            postsController.delete(new IdDto(id));
        }
    },
    UPDATE("/edit") {
        @Override
        public void execute(Request uriRequest, PostsController postsController) {
            String id = uriRequest.getParameter("postId");
            IdDto idDto = new IdDto(id);
            postsController.containId(idDto);
            PostInfoDto postInfoDto = postsController.readUpdateInfo(idDto);
            postsController.update(idDto, postInfoDto);
        }
    };


    private final String path;

    CommandUrl(String path) {
        this.path = path;
    }


    public static Optional<CommandUrl> findPath(String path){
        return Arrays.stream(values()).filter((value)->path.contains(value.path)).findFirst();
    }

    public abstract void execute(Request uriRequest, PostsController postsController);
}
