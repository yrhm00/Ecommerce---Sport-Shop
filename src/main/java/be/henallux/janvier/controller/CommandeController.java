package be.henallux.janvier.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import be.henallux.janvier.model.Cart;
import be.henallux.janvier.service.OrderService;

@Controller
@RequestMapping(value="/commandes")
public class CommandeController {

    private final OrderService orderService;
    private final be.henallux.janvier.service.PromotionService promotionService;
    private final be.henallux.janvier.service.PaymentService paymentService;

    @Autowired
    public CommandeController(OrderService orderService, 
                              be.henallux.janvier.service.PromotionService promotionService,
                              be.henallux.janvier.service.PaymentService paymentService) {
        this.orderService = orderService;
        this.promotionService = promotionService;
        this.paymentService = paymentService;
    }

    /**
     * Affiche le récapitulatif avant confirmation
     */
    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/connexion";
        }
        
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/panier";
        }
        
        // Recalculer la promo pour être sûr
        java.math.BigDecimal discount = promotionService.calculateDiscount(cart.getTotal());
        cart.setDiscountAmount(discount);
        
        model.addAttribute("cart", cart);
        return "checkout"; // Tiles Definition: page de confirmation
    }

    /**
     * Confirme la commande
     */
    @PostMapping("/confirmer")
    public String confirmOrder(HttpSession session, Principal principal, 
                               javax.servlet.http.HttpServletRequest request,
                               Model model) {
        if (principal == null) {
            return "redirect:/connexion";
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getItems().isEmpty()) {
            
            String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            String returnUrl = baseUrl + "/commandes/pay/success";
            String cancelUrl = baseUrl + "/commandes/pay/cancel";
            
            String approvalLink = paymentService.createOrder(cart.getTotalWithDiscount(), returnUrl, cancelUrl);
            
            if (approvalLink != null) {
                return "redirect:" + approvalLink;
            }
            
            model.addAttribute("paymentError", "Erreur lors de l'initialisation du paiement PayPal.");
            model.addAttribute("cart", cart);
            return "checkout";
        }
        
        return "redirect:/produits";
    }

    @GetMapping("/pay/success")
    public String handlePaySuccess(@org.springframework.web.bind.annotation.RequestParam("token") String token, HttpSession session, Principal principal) {
        if (paymentService.captureOrder(token)) {
            Cart cart = (Cart) session.getAttribute("cart");
            orderService.createOrder(cart, principal.getName(), true);
            return "redirect:/commandes/succes";
        }
        return "redirect:/commandes/checkout?error=payment_failed";
    }

    @GetMapping("/pay/cancel")
    public String handlePayCancel() {
        return "redirect:/commandes/checkout?error=payment_cancelled";
    }

    /**
     * Page de succès
     */
    @GetMapping("/succes")
    public String success() {
        return "commande-succes"; // Tiles Definition
    }
}
