package org.example.user.persentation.command.flow;

import org.example.boards.presentation.command.ValidationLongConverter;
import org.example.dispatcher.dto.Request;
import org.example.global.ExceptionBoxCommandFlow;
import org.example.user.persentation.command.Command;
import org.example.user.persentation.controller.UserController;

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
