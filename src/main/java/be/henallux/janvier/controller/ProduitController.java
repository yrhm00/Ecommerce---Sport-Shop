package be.henallux.janvier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import be.henallux.janvier.model.Category;
import be.henallux.janvier.model.Product;
import be.henallux.janvier.service.CategoryService;
import be.henallux.janvier.service.ProductService;

@Controller
@RequestMapping(value = "/produits")
public class ProduitController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public ProduitController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    /**
     * Affiche la liste des catégories
     */
    @GetMapping
    public String showCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "produits"; // Tiles Definition
    }

    /**
     * Affiche les produits d'une catégorie
     */
    @GetMapping("/categorie/{categoryId}")
    public String showProductsByCategory(@PathVariable Integer categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId);
        List<Product> products = productService.getProductsByCategory(categoryId);

        model.addAttribute("category", category);
        model.addAttribute("products", products);
        return "produits-liste";
    }

    /**
     * Affiche les détails d'un produit
     */
    @GetMapping("/{productId}")
    public String showProductDetails(@PathVariable Integer productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "produit-detail";
    }

    @GetMapping("/nouveautes")
    public String showNewArrivals(Model model) {
        List<Product> products = productService.getNewArrivals();

        // Création d'une catégorie virtuelle pour l'affichage
        Category category = new Category();
        category.setNom("Nouveautés");

        model.addAttribute("category", category);
        model.addAttribute("products", products);
        return "produits-liste";
    }

    @GetMapping("/promotions")
    public String showPromotions(Model model) {
        List<Product> products = productService.getPromotions();

        // Création d'une catégorie virtuelle pour l'affichage
        Category category = new Category();
        category.setNom("Promotions");

        model.addAttribute("category", category);
        model.addAttribute("products", products);
        return "produits-liste";
    }
}
