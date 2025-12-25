package be.henallux.janvier.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.henallux.janvier.model.Cart;
import be.henallux.janvier.model.Product;
import be.henallux.janvier.service.ProductService;

@Controller
@RequestMapping(value="/panier")
public class PanierController {

    private static final String CART_SESSION_KEY = "cart";

    private final ProductService productService;
    private final be.henallux.janvier.service.PromotionService promotionService;

    @Autowired
    public PanierController(ProductService productService, be.henallux.janvier.service.PromotionService promotionService) {
        this.productService = productService;
        this.promotionService = promotionService;
    }

    /**
     * Affiche le contenu du panier
     */
    @GetMapping
    public String showPanier(HttpSession session, Model model) {
        Cart cart = getCart(session);
        
        // Calculer la promotion
        java.math.BigDecimal discount = promotionService.calculateDiscount(cart.getTotal());
        cart.setDiscountAmount(discount);
        
        model.addAttribute("cart", cart);
        return "panier";
    }

    /**
     * Ajoute un produit au panier
     */
    @GetMapping("/ajouter/{productId}")
    public String addToCart(@PathVariable Integer productId,
                            @RequestParam(defaultValue = "1") Integer quantite,
                            @RequestParam(required = false) String taille,
                            HttpSession session) {
        Product product = productService.getProductById(productId);
        
        if (product != null) {
            // Vérifier que la quantité est positive
            if (quantite <= 0) {
                return "redirect:/panier";
            }

            // Vérifier le stock spécifique à la taille si une taille est fournie
            boolean stockSuffisant = false;
            if (taille != null && !taille.isEmpty() && product.getSizesStock() != null && product.getSizesStock().containsKey(taille)) {
                 stockSuffisant = product.getSizesStock().get(taille) >= quantite;
            } else {
                 // Fallback au stock global si pas de taille ou pas de gestion de taille
                 stockSuffisant = product.getStock() >= quantite;
            }

            if (stockSuffisant) {
                Cart cart = getCart(session);
                cart.addItem(product, quantite, taille);
                session.setAttribute(CART_SESSION_KEY, cart);
            }
        }
        
        return "redirect:/panier";
    }

    /**
     * Modifie la quantité d'un produit dans le panier
     */
    @PostMapping("/modifier")
    public String updateQuantity(@RequestParam Integer productId,
                                 @RequestParam Integer quantite,
                                 @RequestParam(required = false) String taille,
                                 HttpSession session) {
        if (quantite <= 0) {
             return "redirect:/panier";
        }
        Cart cart = getCart(session);
        cart.updateQuantity(productId, quantite, taille);
        session.setAttribute(CART_SESSION_KEY, cart);
        
        return "redirect:/panier";
    }

    /**
     * Supprime un produit du panier
     */
    @GetMapping("/supprimer/{productId}")
    public String removeFromCart(@PathVariable Integer productId,
                                 @RequestParam(required = false) String taille,
                                 HttpSession session) {
        Cart cart = getCart(session);
        cart.removeItem(productId, taille);
        session.setAttribute(CART_SESSION_KEY, cart);
        
        return "redirect:/panier";
    }

    /**
     * Vide complètement le panier
     */
    @GetMapping("/vider")
    public String clearCart(HttpSession session) {
        Cart cart = getCart(session);
        cart.clear();
        session.setAttribute(CART_SESSION_KEY, cart);
        
        return "redirect:/panier";
    }

    /**
     * Récupère le panier de la session ou en crée un nouveau
     */
    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new Cart();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }
}
