package org.example.account.persentation.command.flow;

import org.example.account.persentation.command.Command;
import org.example.account.persentation.controller.UserController;
import org.example.boards.presentation.command.ValidationLongConverter;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;

public class RemoveFlow extends CommandFlow<Command> {

    private final UserController controller;

    public RemoveFlow(UserController controller) {
        super(Command.REMOVE);
        this.controller = controller;
    }

    @Override
    public void execute(Request request, Response response) {
        String id = request.getParameter("accountId");
        controller.remove(ValidationLongConverter.convert(id));
    }
}
