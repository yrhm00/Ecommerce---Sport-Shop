package be.henallux.janvier.service;

import be.henallux.janvier.model.Cart;
import be.henallux.janvier.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {

    public CartService() {
    }

    public void addItem(Cart cart, Product product, int quantity, String size) {
        cart.addItem(product, quantity, size);
        recalculateCart(cart);
    }

    public void removeItem(Cart cart, int productId, String size) {
        cart.removeItem(productId, size);
        recalculateCart(cart);
    }

    public void updateQuantity(Cart cart, int productId, int quantity, String size) {
        cart.updateQuantity(productId, quantity, size);
        recalculateCart(cart);
    }

    public void clear(Cart cart) {
        cart.clear();
        recalculateCart(cart);
    }

    /**
     * Recalculer le total et appliquer les promotions (Accessible publiquement)
     */
    public void recalculateCart(Cart cart) {
        BigDecimal total = cart.getTotal();
        BigDecimal discount = BigDecimal.ZERO;

        // Promotion: -10% si > 100€
        if (total.compareTo(new BigDecimal("100.00")) > 0) {
            discount = total.multiply(new BigDecimal("0.10"));
        }

        // Arrondir le montant de la remise à 2 décimales
        discount = discount.setScale(2, java.math.RoundingMode.HALF_UP);

        cart.setDiscountAmount(discount);
    }
}
