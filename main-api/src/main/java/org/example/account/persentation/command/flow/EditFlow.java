package org.example.account.persentation.command.flow;

import org.example.account.persentation.command.Command;
import org.example.account.persentation.controller.UserController;
import org.example.account.persentation.dto.UserInfoDto;
import org.example.boards.presentation.command.ValidationLongConverter;
import org.example.dispatcher.dto.Request;
import org.example.global.ExceptionBoxCommandFlow;

public class EditFlow extends ExceptionBoxCommandFlow<Command> {

    private final UserController controller;

    public EditFlow(UserController controller) {
        super(Command.EDIT);
        this.controller = controller;
    }

    @Override
    public void doAfter(Request request) {
        Long id = ValidationLongConverter.convert(request.getParameter("accountId"));
        UserInfoDto userInfoDto = controller.readUpdateInfo();
        controller.edit(id,userInfoDto);
    }
}
