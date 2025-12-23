package be.henallux.janvier.controller;

import be.henallux.janvier.model.InscriptionForm;
import be.henallux.janvier.model.User;
import be.henallux.janvier.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value="/inscription")
public class InscriptionController {

    private final InscriptionService inscriptionService;

    @Autowired
    public InscriptionController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @GetMapping
    public String showInscription(Model model) {
        model.addAttribute("inscriptionForm", new InscriptionForm());
        return "inscription";
    }

    @PostMapping
    public String processInscription(@Valid @ModelAttribute("inscriptionForm") InscriptionForm form,
                                      BindingResult bindingResult,
                                      Model model) {
        
        // Vérification des erreurs de validation
        if (bindingResult.hasErrors()) {
            return "inscription";
        }

        // Vérification que les mots de passe correspondent
        if (!inscriptionService.passwordsMatch(form.getPassword(), form.getConfirmPassword())) {
            model.addAttribute("errorMessage", "Les mots de passe ne correspondent pas");
            return "inscription";
        }

        // Vérification que le username n'existe pas déjà
        if (inscriptionService.usernameExists(form.getUsername())) {
            model.addAttribute("errorMessage", "Ce nom d'utilisateur existe déjà");
            return "inscription";
        }

        // Vérification que l'email n'existe pas déjà
        if (inscriptionService.emailExists(form.getEmail())) {
            model.addAttribute("errorMessage", "Cet email est déjà utilisé");
            return "inscription";
        }

        // Créer l'utilisateur
        User user = inscriptionService.createUser(form);

        // Redirection vers la page de connexion avec un message de succès
        model.addAttribute("successMessage", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
        return "redirect:/connexion?inscriptionSuccess";
    }
}
