package be.henallux.janvier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.henallux.janvier.model.Cart;
import be.henallux.janvier.model.Product;

public class CartServiceTest {

    private CartService cartService;
    private Cart cart;

    @BeforeEach
    public void setUp() {
        cartService = new CartService();
        cart = new Cart();
    }

    @Test
    public void testNoDiscountUnder100() {
        Product product = new Product();
        product.setId(1);
        product.setPrix(new BigDecimal("50.00"));

        cartService.addItem(cart, product, 1, "M");

        // Total = 50. Discount = 0.
        assertEquals(new BigDecimal("50.00"), cart.getTotal());
        assertEquals(new BigDecimal("0.00"), cart.getDiscountAmount());
        assertEquals(new BigDecimal("50.00"), cart.getTotalWithDiscount());
    }

    @Test
    public void testDiscountOver100() {
        Product product = new Product();
        product.setId(1);
        product.setPrix(new BigDecimal("150.00"));

        cartService.addItem(cart, product, 1, "M");

        // Total = 150. Discount = 15 (10%). TotalWithDiscount = 135.
        // Discount setScale(2) -> 15.00
        assertEquals(new BigDecimal("150.00"), cart.getTotal());
        assertEquals(new BigDecimal("15.00"), cart.getDiscountAmount());
        assertEquals(new BigDecimal("135.00"), cart.getTotalWithDiscount());
    }

    @Test
    public void testDiscountExact100() {
        Product product = new Product();
        product.setId(1);
        product.setPrix(new BigDecimal("100.00"));

        cartService.addItem(cart, product, 1, "M");

        // Total = 100. Discount = 0 (Condition is > 100).
        assertEquals(new BigDecimal("100.00"), cart.getTotal());
        BigDecimal discount = cart.getDiscountAmount();
        // Depending on defaulting, might be 0 or 0.00.
        // CartService initializes discountAmount = Zero.
        // Recalculate logic: if > 100 set discount, else discount = 0 (logic I wrote?
        // Let's check logic)
        // My Logic: BigDecimal discount = BigDecimal.ZERO; if(>100) discount = ...;
        // cart.setDiscountAmount(discount);
        // So it is 0.
        // Compare with compareTo because scale might differ (0 vs 0.00) if not set.
        assertEquals(0, BigDecimal.ZERO.compareTo(discount));
    }
}
