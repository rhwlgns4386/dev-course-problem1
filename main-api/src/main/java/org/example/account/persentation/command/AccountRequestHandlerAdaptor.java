package org.example.account.persentation.command;

import org.example.account.persentation.command.flow.*;
import org.example.account.persentation.controller.AccountController;
import org.example.account.persentation.controller.UserController;
import org.example.cli.CommandFlow;
import org.example.cli.CommandFlowFinder;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;
import org.example.global.BaseRequestHandler;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.ExceptionHandler;

import java.util.List;
import java.util.Optional;

public class AccountRequestHandlerAdaptor extends BaseRequestHandler {

    private final String PREFIX = "/account";
    private final CommandFlowFinder<Command> finder;

    public AccountRequestHandlerAdaptor(UserController userController,AccountController accountController, ExceptionHandler exceptionHandler) {
        super(exceptionHandler);
        List<CommandFlow<Command>> commandFlows = List.of(new DetailsFlow(userController), new SingupFlow(userController), new RemoveFlow(userController), new EditFlow(userController),new SignInFlow(accountController));
        this.finder = new CommandFlowFinder<>(commandFlows);
    }

    @Override
    public boolean support(Request request) {
        String command = request.getUrl();
        return command.startsWith(PREFIX);
    }

    @Override
    public void execute(Request commandInput, Response response) {
        Command command = Command.findPath(extractCommandString(commandInput)).orElseThrow(() -> new CommandNotFoundException("존재하지 않는 명령어 입니다."));
        Optional<CommandFlow<Command>> flow = finder.find(command);
        flow.ifPresent(commandFlow -> commandFlow.execute(commandInput, response));
    }

    private String extractCommandString(Request commandInput) {
        String url = commandInput.getUrl();
        return url.substring(PREFIX.length());
    }
}
