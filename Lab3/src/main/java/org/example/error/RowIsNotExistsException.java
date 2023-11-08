package org.example.error;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "org.example.error.RowIsNotExistsFault")
public class RowIsNotExistsException extends Exception {
    private final RowIsNotExistsFault fault;

    public RowIsNotExistsException(String message, RowIsNotExistsFault fault) {
        super(message);
        this.fault = fault;
    }

    public RowIsNotExistsException(String message, RowIsNotExistsFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public RowIsNotExistsFault getFaultInfo() {
        return fault;
    }
}
