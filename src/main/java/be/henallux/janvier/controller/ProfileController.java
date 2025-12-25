package be.henallux.janvier.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import be.henallux.janvier.dataAccess.dao.UserDAO;
import be.henallux.janvier.model.User;

@Controller
@RequestMapping(value="/profil")
public class ProfileController {

    private final UserDAO userDAO;

    @Autowired
    public ProfileController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    public String showProfile(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/connexion";
        }
        
        String username = authentication.getName();
        User user = userDAO.findByUsername(username);
        
        if (user == null) {
            return "redirect:/deconnexion";
        }
        
        // On ne veut pas afficher le mot de passe hashé
        user.setPassword(null);
        
        model.addAttribute("user", user);
        return "profil";
    }

    @PostMapping
    public String updateProfile(@Valid @ModelAttribute("user") User formUser,
                               BindingResult bindingResult,
                               Model model,
                               Authentication authentication) {
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/connexion";
        }

        if (bindingResult.hasErrors()) {
            return "profil";
        }

        String currentUsername = authentication.getName();
        User currentUser = userDAO.findByUsername(currentUsername);
        
        // Vérifier que l'utilisateur existe
        if (currentUser == null) {
            model.addAttribute("errorMessage", "Utilisateur introuvable.");
            return "profil";
        }
        
        // Empêcher la modification du username si le formulaire a été trafiqué
        if (!currentUsername.equals(formUser.getUsername())) {
             model.addAttribute("errorMessage", "Impossible de modifier le nom d'utilisateur.");
             return "profil";
        }

        // Mise à jour des champs autorisés
        currentUser.setNom(formUser.getNom());
        currentUser.setPrenom(formUser.getPrenom());
        currentUser.setEmail(formUser.getEmail());
        currentUser.setTelephone(formUser.getTelephone());
        currentUser.setAdresse(formUser.getAdresse());
        
        // On garde le mot de passe existant (car formUser.password est null ou vide)
        // On garde enabled et authorities
        
        try {
            userDAO.save(currentUser);
            model.addAttribute("successMessage", "Profil mis à jour avec succès!");
            // Recharger l'utilisateur pour l'affichage
            // Note: formUser a des champs nulls (pwd), on renvoie currentUser
            // Mais on doit masquer le password
            currentUser.setPassword(null);
            model.addAttribute("user", currentUser); 
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erreur lors de la mise à jour: " + e.getMessage());
            return "profil";
        }

        return "profil";
    }
}
