package org.example.account.persentation.command;

import org.example.account.persentation.command.flow.*;
import org.example.account.persentation.controller.AccountController;
import org.example.account.persentation.controller.UserController;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.global.BaseRequestHandler;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.ExceptionHandler;

import java.util.List;

public class AccountRequestHandlerAdaptor extends BaseRequestHandler<Command> {

    private final String PREFIX = "/account";

    public AccountRequestHandlerAdaptor(UserController userController, AccountController accountController, ExceptionHandler exceptionHandler) {
        super(exceptionHandler, commandFlows(userController, accountController));
    }

    private static List<CommandFlow<Command>> commandFlows(UserController userController, AccountController accountController) {
        return List.of(new DetailsFlow(userController), new SingupFlow(userController), new RemoveFlow(userController), new EditFlow(userController), new SignInFlow(accountController), new SignoutFlow(accountController));
    }

    @Override
    public boolean support(Request request) {
        String command = request.getUrl();
        return command.startsWith(PREFIX);
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
