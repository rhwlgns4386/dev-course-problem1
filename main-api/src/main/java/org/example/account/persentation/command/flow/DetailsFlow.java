package org.example.account.persentation.command.flow;

import org.example.account.persentation.command.Command;
import org.example.account.persentation.controller.UserController;
import org.example.boards.presentation.command.ValidationLongConverter;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.validator.StringValidator;

public class DetailsFlow extends CommandFlow<Command, UserController> {
    public DetailsFlow() {
        super(Command.DETAIL);
    }

    @Override
    public void execute(UserController controller, Request request) {
        String id = request.getParameter("accountId");
        StringValidator.validate(id);
        controller.load(ValidationLongConverter.convert(id));
    }
}
