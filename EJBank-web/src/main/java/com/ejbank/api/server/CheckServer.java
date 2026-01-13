/**
 * API for server health check.
 * Provides endpoints to check server status.
 */
package com.ejbank.api.server;

import com.ejbank.api.server.payload.ServerPayload;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/server")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CheckServer {

    /**
     * Endpoint to get server status.
     * @return A payload indicating the server status.
     */
    @GET
    @Path("/status")
    public ServerPayload getStatus() {
        return new ServerPayload(true);
    }
}
