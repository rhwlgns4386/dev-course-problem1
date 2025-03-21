package org.example.account.persentation.command.flow;

import org.example.account.persentation.command.Command;
import org.example.account.persentation.controller.UserController;
import org.example.boards.presentation.command.ValidationLongConverter;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;

public class RemoveFlow extends CommandFlow<Command, UserController> {

    public RemoveFlow() {
        super(Command.REMOVE);
    }

    @Override
    public void execute(UserController controller, Request request) {
        String id = request.getParameter("accountId");
        controller.remove(ValidationLongConverter.convert(id));
    }
}
