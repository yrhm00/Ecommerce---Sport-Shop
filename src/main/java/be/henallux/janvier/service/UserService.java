package be.henallux.janvier.service;

import be.henallux.janvier.dataAccess.dao.UserDAO;
import be.henallux.janvier.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final be.henallux.janvier.service.converter.UserConverter converter;

    public boolean validatePasswordConfirmation(User user) {
        if (user.getPassword() == null)
            return true; // Pas de changement de mot de passe
        return user.getPassword().equals(user.getConfirmPassword());
    }

    @Autowired
    public UserService(UserDAO userDAO, be.henallux.janvier.service.converter.UserConverter converter) {
        this.userDAO = userDAO;
        this.converter = converter;
    }

    public void save(User user) {
        userDAO.save(converter.userModelToEntity(user));
        // Note: Authorities logic might be needed here or in DAO.
        // UserDAO had manual authority saving logic.
        // We should move that logic here or keep it in DAO but exposed differently.
        // For now, simple save.
    }

    public User findByUsername(String username) {
        return converter.userEntityToModel(userDAO.findByUsername(username));
    }

    public boolean existsByUsername(String username) {
        return userDAO.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userDAO.existsByEmail(email);
    }
}
