package org.example.account.persentation.command.flow;

import org.example.account.persentation.command.Command;
import org.example.account.persentation.controller.UserController;
import org.example.account.persentation.dto.UserInfoDto;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;

public class SingupFlow extends CommandFlow<Command> {

    private final UserController controller;

    public SingupFlow(UserController controller) {
        super(Command.SIGNUP);
        this.controller = controller;
    }

    @Override
    public void execute(Request request) {
        UserInfoDto userInfoDto = controller.readUserInfo();
        controller.singup(userInfoDto);
    }
}
