package org.example.global;

import org.example.account.persentation.command.Command;
import org.example.cli.CommandFlow;
import org.example.cli.CommandFlowFinder;
import org.example.dispatcher.RequestHandler;
import org.example.dispatcher.dto.Request;
import org.example.global.exception.CommandNotFoundException;
import org.example.global.exception.ExceptionHandler;
import org.example.global.exception.FormatException;
import org.example.global.exception.PresentationException;

import java.util.List;
import java.util.Optional;

public abstract class BaseRequestHandler<K> implements RequestHandler {

    private final ExceptionHandler exceptionHandler;
    private final CommandFlowFinder<K> finder;

    public BaseRequestHandler(ExceptionHandler exceptionHandler,List<CommandFlow<K>> commandFlows) {
        this.exceptionHandler = exceptionHandler;
        this.finder = new CommandFlowFinder<>(commandFlows);
    }

    @Override
    public void run(Request request) {
        try {
            Optional<CommandFlow<K>> flow = finder.find(extractCommandKey(request));
            flow.ifPresent(commandFlow -> commandFlow.execute(request));
        }catch (Exception e) {
            handleException(e);
        }
    }

    private void handleException(Exception e) {
        if(e instanceof PresentationException){
            exceptionHandler.handlePresentationException((PresentationException) e);
        }else if(e instanceof FormatException){
            exceptionHandler.handleFormatException(e);
        }else if(e instanceof IllegalArgumentException){
            exceptionHandler.handleIllegaltException((IllegalArgumentException) e);
        }else{
            exceptionHandler.handleRuntimeException(e);
        }

    }

    public abstract K extractCommandKey(Request request);
}
