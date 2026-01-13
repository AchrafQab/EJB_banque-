/**
 * Service interface for user-related operations.
 */
package com.ejbank.service.user;

import com.ejbank.dto.user.UserData;

import javax.ejb.Local;

@Local
public interface UserService {

    /**
     * Retrieves user details by their ID.
     */
    UserData getUserById(Integer id);
}
