package be.henallux.janvier.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PromotionServiceTest {

    private PromotionService promotionService;

    @BeforeEach
    public void setUp() {
        promotionService = new PromotionService();
    }

    @Test
    public void testCalculateDiscountWithNullTotal() {
        assertEquals(BigDecimal.ZERO, promotionService.calculateDiscount(null));
    }

    @Test
    public void testCalculateDiscountWithTotalUnderThreshold() {
        BigDecimal total = new BigDecimal("50.00");
        assertEquals(BigDecimal.ZERO, promotionService.calculateDiscount(total));
    }

    @Test
    public void testCalculateDiscountWithTotalEqualThreshold() {
        BigDecimal total = new BigDecimal("100.00");
        assertEquals(BigDecimal.ZERO, promotionService.calculateDiscount(total));
    }

    @Test
    public void testCalculateDiscountWithTotalOverThreshold() {
        BigDecimal total = new BigDecimal("200.00");
       
        BigDecimal expected = new BigDecimal("20.000"); 
        
        assertEquals(0, expected.compareTo(promotionService.calculateDiscount(total)));
    }
}
