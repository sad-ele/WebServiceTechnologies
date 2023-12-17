package com.example.lab5.errors;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RowIsNotExistsExceptionMapper implements ExceptionMapper<RowIsNotExistsException> {
    @Override
    public Response toResponse(RowIsNotExistsException ex) {
        return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
    }
}
