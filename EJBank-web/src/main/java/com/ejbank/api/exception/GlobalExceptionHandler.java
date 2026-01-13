package com.ejbank.api.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        String errorMessage = exception.getMessage() != null ? exception.getMessage() : "Unknown error";

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"error\": \"" + errorMessage + "\"}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
