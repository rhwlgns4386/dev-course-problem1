package org.example.account.persentation.command;

import org.example.account.persentation.command.flow.DetailsFlow;
import org.example.account.persentation.command.flow.SingupFlow;
import org.example.account.persentation.controller.UserController;
import org.example.cli.CommandFlow;
import org.example.cli.CommandFlowFinder;
import org.example.dispatcher.dto.Request;
import org.example.global.BaseRequestHandler;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.ExceptionHandler;

import java.util.List;
import java.util.Optional;

public class AccountRequestHandlerAdaptor extends BaseRequestHandler {

    private final String PREFIX = "/account";
    private final UserController userController;
    private final CommandFlowFinder<Command,UserController> finder;

    public AccountRequestHandlerAdaptor(UserController userController, ExceptionHandler exceptionHandler) {
        super(exceptionHandler);
        this.userController = userController;
        List<CommandFlow<Command, UserController>> commandFlows = List.of(new DetailsFlow(), new SingupFlow());
        this.finder = new CommandFlowFinder<>(commandFlows);
    }

    @Override
    public boolean support(Request request) {
        String command = request.getUrl();
        return command.startsWith(PREFIX);
    }

    @Override
    public void execute(Request commandInput) {
        Command command = Command.findPath(extractCommandString(commandInput)).orElseThrow(()->new CommandNotFoundException("존재하지 않는 명령어 입니다."));
        Optional<CommandFlow<Command, UserController>> flow = finder.find(command);
        flow.ifPresent(commandFlow -> commandFlow.execute(userController, commandInput));
    }

    private String extractCommandString(Request commandInput) {
        String url = commandInput.getUrl();
        return url.substring(PREFIX.length());
    }
}
