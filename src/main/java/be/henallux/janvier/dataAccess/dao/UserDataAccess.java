package be.henallux.janvier.dataAccess.dao;

import be.henallux.janvier.model.User;

/**
 * Interface DataAccess pour les utilisateurs
 * Expose les méthodes aux couches supérieures (pas d'entités !)
 */
public interface UserDataAccess {
    
    User save(User user);
    
    User findByUsername(String username);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}


