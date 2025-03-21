package org.example.account.persentation.command.flow;

import org.example.account.persentation.command.Command;
import org.example.account.persentation.controller.AccountController;
import org.example.account.persentation.dto.UserInfoDto;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;

public class SignInFlow extends CommandFlow<Command> {

    private final AccountController controller;

    public SignInFlow(AccountController controller) {
        super(Command.SIGNIN);
        this.controller = controller;
    }

    @Override
    public void execute(Request request, Response response) {
        UserInfoDto userInfoDto = controller.readLoginInfo();
        controller.login(userInfoDto.getUserName(),userInfoDto.getPassword(),response);
    }
}
