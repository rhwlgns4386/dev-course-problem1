package org.example.global;

import org.example.dispatcher.RequestHandler;
import org.example.dispatcher.dto.Request;
import org.example.dispatcher.dto.Response;
import org.example.global.exception.ExceptionHandler;
import org.example.global.exception.FormatException;
import org.example.global.exception.PresentationException;

public abstract class BaseRequestHandler implements RequestHandler {

    private final ExceptionHandler exceptionHandler;

    public BaseRequestHandler(ExceptionHandler exceptionHandler){
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void run(Request request) {
        try {
            execute(request);
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

    public abstract void execute(Request request) throws Exception;
}
