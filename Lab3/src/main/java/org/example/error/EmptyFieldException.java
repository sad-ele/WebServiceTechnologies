package org.example.error;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "org.example.error.EmptyFieldFault")
public class EmptyFieldException extends Exception {

    private final EmptyFieldFault fault;

    public EmptyFieldException(String message, EmptyFieldFault fault) {
        super(message);
        this.fault = fault;
    }

    public EmptyFieldException(String message, EmptyFieldFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public EmptyFieldFault getFaultInfo() {
        return fault;
    }
}
