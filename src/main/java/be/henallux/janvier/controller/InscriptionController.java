package be.henallux.janvier.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import be.henallux.janvier.model.InscriptionForm;

import be.henallux.janvier.service.InscriptionService;

@Controller
@RequestMapping(value = "/inscription")
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
            model.addAttribute("errorMessage", "inscription.error.passwordMismatch");
            return "inscription";
        }

        // Vérification que le username n'existe pas déjà
        if (inscriptionService.usernameExists(form.getUsername())) {
            model.addAttribute("errorMessage", "inscription.error.usernameExists");
            return "inscription";
        }

        // Vérification que l'email n'existe pas déjà
        if (inscriptionService.emailExists(form.getEmail())) {
            model.addAttribute("errorMessage", "inscription.error.emailExists");
            return "inscription";
        }

        // Créer l'utilisateur
        inscriptionService.createUser(form);

        // Redirection vers la page de connexion avec un message de succès
        model.addAttribute("successMessage", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
        return "redirect:/connexion?inscriptionSuccess";
    }
}
