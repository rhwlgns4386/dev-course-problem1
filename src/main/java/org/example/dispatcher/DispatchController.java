package org.example.dispatcher;

import org.example.dispatcher.dto.Request;
import org.example.dispatcher.excepthion.RequestNotMatchedException;

public class DispatchController {

    private final HandlerMapper handlerMapper;

    public DispatchController(HandlerMapper handlerMapper) {
        this.handlerMapper = handlerMapper;
    }

    public void dispatch(Request commandInput){
        try{
            RequestHandler requestHandler = handlerMapper.findRequestHandler(commandInput);
            requestHandler.run(commandInput);
        }catch (RequestNotMatchedException e){
            System.out.println("처리 할 수 없는 요청입니다.");
        }catch (Exception e){
            System.out.println("에외 상황이 발생하였습니다.");
        }
    }
}
