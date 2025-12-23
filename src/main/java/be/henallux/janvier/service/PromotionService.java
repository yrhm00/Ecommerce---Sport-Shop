package be.henallux.janvier.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class PromotionService {

    private static final BigDecimal MIN_AMOUNT_FOR_DISCOUNT = new BigDecimal("100.00");
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.10"); // 10%

    public BigDecimal calculateDiscount(BigDecimal total) {
        if (total == null) {
            return BigDecimal.ZERO;
        }

        if (total.compareTo(MIN_AMOUNT_FOR_DISCOUNT) > 0) {
            return total.multiply(DISCOUNT_RATE);
        }

        return BigDecimal.ZERO;
    }
}
