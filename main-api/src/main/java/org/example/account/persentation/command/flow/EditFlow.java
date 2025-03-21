package org.example.account.persentation.command.flow;

import org.example.account.persentation.command.Command;
import org.example.account.persentation.controller.UserController;
import org.example.account.persentation.dto.UserInfoDto;
import org.example.boards.presentation.command.ValidationLongConverter;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;

public class EditFlow extends CommandFlow<Command, UserController> {
    public EditFlow() {
        super(Command.EDIT);
    }

    @Override
    public void execute(UserController controller, Request request) {
        Long id = ValidationLongConverter.convert(request.getParameter("accountId"));
        UserInfoDto userInfoDto = controller.readUpdateInfo();
        controller.edit(id,userInfoDto);
    }
}
