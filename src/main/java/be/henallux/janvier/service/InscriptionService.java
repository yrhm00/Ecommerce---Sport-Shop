package be.henallux.janvier.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import be.henallux.janvier.dataAccess.dao.UserDataAccess;
import be.henallux.janvier.model.Authority;
import be.henallux.janvier.model.InscriptionForm;
import be.henallux.janvier.model.User;

@Service
public class InscriptionService {

    private final UserDataAccess userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InscriptionService(UserDataAccess userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Valide que les deux mots de passe sont identiques
     */
    public boolean passwordsMatch(String password, String confirmPassword) {
        return password != null && password.equals(confirmPassword);
    }

    /**
     * Vérifie si le username existe déjà
     */
    public boolean usernameExists(String username) {
        return userDAO.existsByUsername(username);
    }

    /**
     * Vérifie si l'email existe déjà
     */
    public boolean emailExists(String email) {
        return userDAO.existsByEmail(email);
    }

    /**
     * Crée un nouvel utilisateur à partir du formulaire d'inscription
     */
    public User createUser(InscriptionForm form) {
        // Créer l'utilisateur
        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword())); // Hash BCrypt
        user.setNom(form.getNom());
        user.setPrenom(form.getPrenom());
        user.setEmail(form.getEmail());
        user.setTelephone(form.getTelephone());
        user.setAdresse(form.getAdresse());
        user.setCodePostal(form.getCodePostal());
        user.setLocalite(form.getLocalite());
        user.setEnabled(true);

        // Attribuer le rôle ROLE_USER par défaut
        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority("ROLE_USER"));
        user.setAuthorities(authorities);

        // Sauvegarder en base de données
        return userDAO.save(user);
    }
}


