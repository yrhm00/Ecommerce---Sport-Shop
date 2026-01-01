package be.henallux.janvier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class MainController {

    private final be.henallux.janvier.service.CategoryService categoryService;

    @org.springframework.beans.factory.annotation.Autowired
    public MainController(be.henallux.janvier.service.CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "accueil";
    }
}
