package org.example.boards.presentation.command.flow;

import org.example.boards.presentation.command.BoardCommand;
import org.example.boards.presentation.controller.BoardController;
import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;

public class AddFlow extends CommandFlow<BoardCommand, BoardController> {

    public AddFlow() {
        super(BoardCommand.ADD);
    }

    @Override
    public void execute(BoardController controller, Request request) {
        String title = controller.readBoardName();
        controller.write(title);
    }
}
