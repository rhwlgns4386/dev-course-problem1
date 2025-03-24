package org.example.user.persentation.command;

import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.global.BaseRequestHandler;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.ExceptionHandler;
import org.example.user.persentation.command.flow.*;
import org.example.user.persentation.controller.UserController;

import java.util.List;
import java.util.Optional;

public class UserRequestHandlerAdaptor extends BaseRequestHandler<Command> {

    private final String PREFIX = "/account";

    public UserRequestHandlerAdaptor(UserController userController, ExceptionHandler exceptionHandler) {
        super(exceptionHandler, commandFlows(userController));
    }

    private static List<CommandFlow<Command>> commandFlows(UserController userController) {
        return List.of(new DetailsFlow(userController), new SingupFlow(userController), new RemoveFlow(userController), new EditFlow(userController));
    }

    @Override
    public boolean support(Request request) {
        String command = request.getUrl();
        if(command.startsWith(PREFIX)){
            Optional<Command> path = Command.findPath(extractCommandString(request));
            if(path.isPresent()){
                return true;
            }
        }
        return false;
    }

    @Override
    public Command extractCommandKey(Request request) {
        return Command.findPath(extractCommandString(request)).orElseThrow(() -> new CommandNotFoundException("존재하지 않는 명령어 입니다."));
    }

    private String extractCommandString(Request commandInput) {
        String url = commandInput.getUrl();
        return url.substring(PREFIX.length());
    }
}
