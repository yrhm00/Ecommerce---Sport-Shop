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
import be.henallux.janvier.service.CartService;
import be.henallux.janvier.service.ProductService;

@Controller
@RequestMapping(value = "/panier")
public class PanierController {

    private static final String CART_SESSION_KEY = "cart";

    private final ProductService productService;
    private final CartService cartService;

    @Autowired
    public PanierController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping
    public String showPanier(HttpSession session, Model model) {
        Cart cart = getCart(session);
        // Recalcul déjà fait à chaque modif, mais par sécurité on peut le refaire ou
        // juste afficher
        // cartService.recalculateCart(cart); // private method, assuming it's up to
        // date
        model.addAttribute("cart", cart);
        return "panier";
    }

    @GetMapping("/ajouter/{productId}")
    public String addToCart(@PathVariable Integer productId,
            @RequestParam(defaultValue = "1") Integer quantite,
            @RequestParam(required = false) String taille,
            HttpSession session) {
        Product product = productService.getProductById(productId);

        if (product != null) {
            if (quantite <= 0)
                return "redirect:/panier";

            boolean stockSuffisant = false;
            // Note: getSizesStock check logic kept simple here or moved to service?
            // Keeping stock check here for now as it redirects.
            if (taille != null && !taille.isEmpty() && product.getSizesStock() != null
                    && product.getSizesStock().containsKey(taille)) {
                stockSuffisant = product.getSizesStock().get(taille) >= quantite;
            } else {
                stockSuffisant = product.getStock() != null && product.getStock() >= quantite;
            }

            if (stockSuffisant) {
                Cart cart = getCart(session);
                cartService.addItem(cart, product, quantite, taille);
                session.setAttribute(CART_SESSION_KEY, cart);
            }
        }

        return "redirect:/panier";
    }

    @PostMapping("/modifier")
    public String updateQuantity(@RequestParam Integer productId,
            @RequestParam Integer quantite,
            @RequestParam(required = false) String taille,
            HttpSession session) {
        if (quantite <= 0)
            return "redirect:/panier";

        Cart cart = getCart(session);
        cartService.updateQuantity(cart, productId, quantite, taille);
        session.setAttribute(CART_SESSION_KEY, cart);

        return "redirect:/panier";
    }

    @GetMapping("/supprimer/{productId}")
    public String removeFromCart(@PathVariable Integer productId,
            @RequestParam(required = false) String taille,
            HttpSession session) {
        Cart cart = getCart(session);
        cartService.removeItem(cart, productId, taille);
        session.setAttribute(CART_SESSION_KEY, cart);

        return "redirect:/panier";
    }

    @GetMapping("/vider")
    public String clearCart(HttpSession session) {
        Cart cart = getCart(session);
        cartService.clear(cart);
        session.setAttribute(CART_SESSION_KEY, cart);

        return "redirect:/panier";
    }

    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new Cart();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }
}
