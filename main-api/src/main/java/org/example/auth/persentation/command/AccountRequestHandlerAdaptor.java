package org.example.auth.persentation.command;

import org.example.auth.persentation.command.flow.*;
import org.example.auth.persentation.controller.AccountController;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.global.BaseRequestHandler;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.ExceptionHandler;

import java.util.List;
import java.util.Optional;

public class AccountRequestHandlerAdaptor extends BaseRequestHandler<Command> {

    private final String PREFIX = "/account";

    public AccountRequestHandlerAdaptor(AccountController accountController, ExceptionHandler exceptionHandler) {
        super(exceptionHandler, commandFlows(accountController));
    }

    private static List<CommandFlow<Command>> commandFlows(AccountController accountController) {
        return List.of(new SignInFlow(accountController), new SignoutFlow(accountController));
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
