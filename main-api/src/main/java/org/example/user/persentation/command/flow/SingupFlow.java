package org.example.user.persentation.command.flow;

import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.user.persentation.command.Command;
import org.example.user.persentation.controller.UserController;
import org.example.user.persentation.dto.UserInfoDto;

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
