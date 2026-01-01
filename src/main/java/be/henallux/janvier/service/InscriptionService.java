package be.henallux.janvier.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import be.henallux.janvier.model.Authority;
import be.henallux.janvier.model.InscriptionForm;
import be.henallux.janvier.model.User;

@Service
public class InscriptionService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InscriptionService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
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
        return userService.existsByUsername(username);
    }

    /**
     * Vérifie si l'email existe déjà
     */
    public boolean emailExists(String email) {
        return userService.existsByEmail(email);
    }

    /**
     * Crée un nouvel utilisateur à partir du formulaire d'inscription
     */
    public User createUser(InscriptionForm form) {
        // Créer l'utilisateur
        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setNom(form.getNom());
        user.setPrenom(form.getPrenom());
        user.setEmail(form.getEmail());
        user.setTelephone(form.getTelephone());
        user.setAdresse(form.getAdresse());
        user.setCodePostal(form.getCodePostal());
        user.setLocalite(form.getLocalite());
        user.setEnabled(true);

        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority("ROLE_USER"));
        user.setAuthorities(authorities);

        // Sauvegarder en base de données
        userService.save(user);
        return user;
    }
}
