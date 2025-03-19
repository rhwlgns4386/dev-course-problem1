package org.example.posts.presentation.command;

import org.example.posts.presentation.controller.PostsController;
import org.example.posts.presentation.dto.request.PostInfoDto;
import org.example.posts.presentation.dto.request.IdDto;

import java.util.Optional;

public enum Command {
    EXIT("종료") {
        @Override
        public void execute(PostsController controller) {
            controller.exit();
        }
    },
    ALL("목록") {
        @Override
        public void execute(PostsController postsController) {
            postsController.all();
        }
    },
    WRITE("작성") {
        @Override
        public void execute(PostsController postsController) {
            postsController.write(postsController.readInfo());
        }
    },
    LOOKUP("조회") {
        @Override
        public void execute(PostsController postsController) {
            String id = postsController.readLookUpId();
            postsController.lookup(new IdDto(id));
        }
    },
    DELETE("삭제") {
        @Override
        public void execute(PostsController postsController) {
            String id = postsController.readDeleteId();
            postsController.delete(new IdDto(id));
        }
    },
    UPDATE("수정") {
        @Override
        public void execute(PostsController postsController) {
            String id = postsController.readUpdateId();
            IdDto idDto = new IdDto(id);
            postsController.containId(idDto);
            PostInfoDto postInfoDto = postsController.readUpdateInfo(idDto);
            postsController.update(idDto, postInfoDto);
        }
    };


    private final String krCommand;
    Command(String krCommand) {
        this.krCommand = krCommand;
    }

    public static Optional<Command> findByName(String commandName) {
        for (Command command : Command.values()) {
            if (command.name().equalsIgnoreCase(commandName) || command.krCommand.equals(commandName)) {
                return Optional.of(command);
            }
        }
        return Optional.empty();
    }

    public abstract void execute(PostsController postsController);
}
