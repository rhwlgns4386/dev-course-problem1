package org.example.article.cli.command;

import org.example.article.cli.controller.CommandController;
import org.example.article.cli.dto.request.ArticleInfoDto;
import org.example.article.cli.dto.request.IdDto;

import java.util.Optional;

public enum Command {
    EXIT("종료") {
        @Override
        public void execute(CommandController controller) {
            controller.exit();
        }
    },
    ALL("목록") {
        @Override
        public void execute(CommandController commandController) {
            commandController.all();
        }
    },
    WRITE("작성") {
        @Override
        public void execute(CommandController commandController) {
            commandController.write(commandController.readInfo());
        }
    },
    LOOKUP("조회") {
        @Override
        public void execute(CommandController commandController) {
            String id = commandController.readLookUpId();
            commandController.lookup(new IdDto(id));
        }
    },
    DELETE("삭제") {
        @Override
        public void execute(CommandController commandController) {
            String id = commandController.readDeleteId();
            commandController.delete(new IdDto(id));
        }
    },
    UPDATE("수정") {
        @Override
        public void execute(CommandController commandController) {
            String id = commandController.readUpdateId();
            IdDto idDto = new IdDto(id);
            commandController.containId(idDto);
            ArticleInfoDto articleInfoDto = commandController.readUpdateInfo(idDto);
            commandController.update(idDto, articleInfoDto);
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

    public abstract void execute(CommandController commandController);
}
