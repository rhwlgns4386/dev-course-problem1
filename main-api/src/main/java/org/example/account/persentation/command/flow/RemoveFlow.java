package org.example.account.persentation.command.flow;

import org.example.account.persentation.command.Command;
import org.example.account.persentation.controller.UserController;
import org.example.boards.presentation.command.ValidationLongConverter;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;
import org.example.global.ExceptionBoxCommandFlow;

public class RemoveFlow extends ExceptionBoxCommandFlow<Command> {

    private final UserController controller;

    public RemoveFlow(UserController controller) {
        super(Command.REMOVE);
        this.controller = controller;
    }

    @Override
    public void doAfter(Request request) {
        String id = request.getParameter("accountId");
        controller.remove(ValidationLongConverter.convert(id));
    }
}
