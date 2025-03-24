package org.example.global;

import org.example.cli.CommandFlow;
import org.example.dispatcher.dto.Request;
import org.example.global.exception.InvalidParamException;

public abstract class ExceptionBoxCommandFlow<K> extends CommandFlow<K> {
    public ExceptionBoxCommandFlow(K key) {
        super(key);
    }

    @Override
    public void execute(Request request) {
        try {
            doAfter(request);
        }catch (IllegalArgumentException e){
            throw new InvalidParamException("파라미터가 잘못 되었습니다.");
        }
    }

    protected abstract void doAfter(Request request);
}
