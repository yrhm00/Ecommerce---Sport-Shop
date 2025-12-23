package be.henallux.janvier.controller;

import be.henallux.janvier.dataAccess.dao.ProductDataAccess;
import be.henallux.janvier.model.Cart;
import be.henallux.janvier.model.CartItem;
import be.henallux.janvier.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/panier")
public class PanierController {

    private static final String CART_SESSION_KEY = "cart";

    private final ProductDataAccess productDAO;

    @Autowired
    public PanierController(ProductDataAccess productDAO) {
        this.productDAO = productDAO;
    }

    /**
     * Affiche le contenu du panier
     */
    @GetMapping
    public String showPanier(HttpSession session, Model model) {
        Cart cart = getCart(session);
        model.addAttribute("cart", cart);
        return "panier";
    }

    /**
     * Ajoute un produit au panier
     */
    @GetMapping("/ajouter/{productId}")
    public String addToCart(@PathVariable Integer productId,
                            @RequestParam(defaultValue = "1") Integer quantite,
                            HttpSession session) {
        Product product = productDAO.findById(productId);
        
        if (product != null && product.getStock() >= quantite) {
            Cart cart = getCart(session);
            cart.addItem(product, quantite);
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        
        return "redirect:/panier";
    }

    /**
     * Modifie la quantité d'un produit dans le panier
     */
    @PostMapping("/modifier")
    public String updateQuantity(@RequestParam Integer productId,
                                 @RequestParam Integer quantite,
                                 HttpSession session) {
        Cart cart = getCart(session);
        cart.updateQuantity(productId, quantite);
        session.setAttribute(CART_SESSION_KEY, cart);
        
        return "redirect:/panier";
    }

    /**
     * Supprime un produit du panier
     */
    @GetMapping("/supprimer/{productId}")
    public String removeFromCart(@PathVariable Integer productId,
                                 HttpSession session) {
        Cart cart = getCart(session);
        cart.removeItem(productId);
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
