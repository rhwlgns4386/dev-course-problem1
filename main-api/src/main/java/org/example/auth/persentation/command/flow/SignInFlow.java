package org.example.auth.persentation.command.flow;

import org.example.auth.persentation.command.Command;
import org.example.auth.persentation.controller.AccountController;
import org.example.auth.persentation.dto.UserInfoDto;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;

public class SignInFlow extends CommandFlow<Command> {

    private final AccountController controller;

    public SignInFlow(AccountController controller) {
        super(Command.SIGNIN);
        this.controller = controller;
    }

    @Override
    public void execute(Request request) {
        UserInfoDto userInfoDto = controller.readLoginInfo();
        controller.login(userInfoDto.getUserName(), userInfoDto.getPassword(), request);
    }
}
