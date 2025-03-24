package org.example.auth.persentation.command.flow;

import org.example.auth.persentation.command.Command;
import org.example.auth.persentation.controller.AccountController;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;

public class SignoutFlow extends CommandFlow<Command> {

    private final AccountController accountController;

    public SignoutFlow(AccountController accountController) {
        super(Command.SIGNOUT);
        this.accountController = accountController;
    }

    @Override
    public void execute(Request request) {
        accountController.singout(request);
    }
}
