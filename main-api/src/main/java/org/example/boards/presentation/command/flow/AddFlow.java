package org.example.boards.presentation.command.flow;

import org.example.boards.presentation.command.BoardCommand;
import org.example.boards.presentation.controller.BoardController;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;

public class AddFlow extends CommandFlow<BoardCommand> {

    private final BoardController controller;

    public AddFlow(BoardController controller) {
        super(BoardCommand.ADD);
        this.controller = controller;
    }

    @Override
    public void execute(Request request) {
        String title = controller.readBoardName();
        controller.write(title);
    }
}
