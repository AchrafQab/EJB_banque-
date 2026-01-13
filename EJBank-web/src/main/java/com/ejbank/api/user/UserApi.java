/**
 * API for managing user-related operations.
 * Provides endpoints to retrieve user details by ID.
 */
package com.ejbank.api.user;

import com.ejbank.api.user.payload.UserPayload;
import com.ejbank.service.user.UserService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class UserApi {

    @EJB
    private UserService userService;

    /**
     * Endpoint to retrieve user details by user ID.
     */
    @GET
    @Path("/{user_id}")
    public UserPayload getUserById(@PathParam("user_id") Integer userId) {

        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive non-null value");
        }

        var userData = userService.getUserById(userId);

        return new UserPayload(userData.getFirstname(), userData.getLastname());
    }
}
