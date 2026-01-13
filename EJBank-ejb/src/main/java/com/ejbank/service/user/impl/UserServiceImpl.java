/**
 * Implementation of the UserService interface.
 */
package com.ejbank.service.user.impl;

import com.ejbank.dao.EjbankUser;
import com.ejbank.dto.user.UserData;
import com.ejbank.service.user.UserService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Stateless
@LocalBean
public class UserServiceImpl implements UserService, Serializable {

    @PersistenceContext
    private EntityManager em;

    @Override
    public UserData getUserById(Integer id) {

        EjbankUser user = em.find(EjbankUser.class, id);

        if (user == null) {
            throw new NoResultException("User not found with ID: " + id);
        }

        return new UserData(user.getFirstname(), user.getLastname());
    }
}

