package be.henallux.janvier.service.converter;

import be.henallux.janvier.dataAccess.entity.UserEntity;
import be.henallux.janvier.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity userModelToEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setEmail(user.getEmail());
        entity.setTelephone(user.getTelephone());
        entity.setNom(user.getNom());
        entity.setPrenom(user.getPrenom());
        entity.setAdresse(user.getAdresse());
        entity.setCodePostal(user.getCodePostal());
        entity.setLocalite(user.getLocalite());
        entity.setEnabled(user.isEnabled());

        // Authorities are handled separately often, or we can map them here
        // Currently skipped to avoid empty method warnings.

        return entity;
    }

    public User userEntityToModel(UserEntity entity) {
        if (entity == null)
            return null;
        User user = new User();
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setEmail(entity.getEmail());
        user.setTelephone(entity.getTelephone());
        user.setNom(entity.getNom());
        user.setPrenom(entity.getPrenom());
        user.setAdresse(entity.getAdresse());
        user.setCodePostal(entity.getCodePostal());
        user.setLocalite(entity.getLocalite());
        user.setEnabled(entity.getEnabled());

        // Map authorities
        // user.setAuthorities(...)
        return user;
    }
}
