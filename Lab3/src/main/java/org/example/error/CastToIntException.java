package org.example.error;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "org.example.error.CastToIntFault")
public class CastToIntException extends Exception {
    private final CastToIntFault fault;

    public CastToIntException(String message, CastToIntFault fault) {
        super(message);
        this.fault = fault;
    }

    public CastToIntException(String message, CastToIntFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public CastToIntFault getFaultInfo() {
        return fault;
    }

}