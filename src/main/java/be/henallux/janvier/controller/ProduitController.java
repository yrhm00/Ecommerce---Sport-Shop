package be.henallux.janvier.controller;

import be.henallux.janvier.dataAccess.dao.CategoryDataAccess;
import be.henallux.janvier.dataAccess.dao.ProductDataAccess;
import be.henallux.janvier.model.Category;
import be.henallux.janvier.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value="/produits")
public class ProduitController {

    private final CategoryDataAccess categoryDAO;
    private final ProductDataAccess productDAO;

    @Autowired
    public ProduitController(CategoryDataAccess categoryDAO, ProductDataAccess productDAO) {
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
    }

    /**
     * Affiche la liste des catégories
     */
    @GetMapping
    public String showCategories(Model model) {
        List<Category> categories = categoryDAO.findAll();
        model.addAttribute("categories", categories);
        return "produits";
    }

    /**
     * Affiche les produits d'une catégorie
     */
    @GetMapping("/categorie/{categoryId}")
    public String showProductsByCategory(@PathVariable Integer categoryId, Model model) {
        Category category = categoryDAO.findById(categoryId);
        List<Product> products = productDAO.findByCategoryId(categoryId);
        
        model.addAttribute("category", category);
        model.addAttribute("products", products);
        return "produits-liste";
    }

    /**
     * Affiche les détails d'un produit
     */
    @GetMapping("/{productId}")
    public String showProductDetails(@PathVariable Integer productId, Model model) {
        Product product = productDAO.findById(productId);
        model.addAttribute("product", product);
        return "produit-detail";
    }
}
