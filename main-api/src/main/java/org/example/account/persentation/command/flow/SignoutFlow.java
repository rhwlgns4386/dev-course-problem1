package org.example.account.persentation.command.flow;

import org.example.account.persentation.command.Command;
import org.example.account.persentation.controller.AccountController;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;

public class SignoutFlow extends CommandFlow<Command> {

    private final AccountController accountController;

    public SignoutFlow(AccountController accountController) {
        super(Command.SIGNOUT);
        this.accountController = accountController;
    }

    @Override
    public void execute(Request request, Response response) {
        accountController.singout(response);
    }
}
