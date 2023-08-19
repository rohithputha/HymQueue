package org.hitro.exceptions;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HymQueueException extends RuntimeException{
    // need some serious re-design...
    private String message;
    public HymQueueException(Exception e){
        super(e);
    }

    public HymQueueException(String message, Exception e){
        super(e);
        this.message = message;
    }

    public HymQueueException(String message){
        super(new RuntimeException(message));
        this.message = message;
    }
}
