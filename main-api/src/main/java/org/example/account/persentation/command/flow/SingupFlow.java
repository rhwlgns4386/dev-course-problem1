package org.example.account.persentation.command.flow;

import org.example.account.persentation.command.Command;
import org.example.account.persentation.controller.UserController;
import org.example.account.persentation.dto.UserInfoDto;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;

public class SingupFlow extends CommandFlow<Command, UserController> {
    public SingupFlow() {
        super(Command.SIGNUP);
    }

    @Override
    public void execute(UserController controller, Request request) {
        UserInfoDto userInfoDto = controller.readUserInfo();
        controller.singup(userInfoDto);
    }
}
