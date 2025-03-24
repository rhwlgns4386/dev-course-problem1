package org.example.user.persentation.command.flow;

import org.example.boards.presentation.command.ValidationLongConverter;
import org.example.dispatcher.dto.Request;
import org.example.global.ExceptionBoxCommandFlow;
import org.example.user.persentation.command.Command;
import org.example.user.persentation.controller.UserController;
import org.example.validator.StringValidator;

public class DetailsFlow extends ExceptionBoxCommandFlow<Command> {

    private final UserController controller;

    public DetailsFlow(UserController userController) {
        super(Command.DETAIL);
        this.controller = userController;
    }

    @Override
    public void doAfter(Request request) {
        String id = request.getParameter("accountId");
        StringValidator.validate(id);
        controller.load(ValidationLongConverter.convert(id));
    }
}
