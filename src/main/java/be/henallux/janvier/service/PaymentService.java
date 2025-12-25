package be.henallux.janvier.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.PurchaseUnitRequest;

@Service
public class PaymentService {

    private final PayPalHttpClient payPalHttpClient;

    @Autowired
    public PaymentService(PayPalHttpClient payPalHttpClient) {
        this.payPalHttpClient = payPalHttpClient;
    }

    public String createOrder(BigDecimal totalAmount, String returnUrl, String cancelUrl) {
        System.out.println("=== PayPal Create Order START ===");
        System.out.println("Amount: " + totalAmount);
        System.out.println("Return URL: " + returnUrl);
        System.out.println("Cancel URL: " + cancelUrl);
        
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.intent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext()
                .brandName("Ecommerce Sport Shop")
                .landingPage("BILLING")
                .cancelUrl(cancelUrl)
                .returnUrl(returnUrl);
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
        
        // Generate a unique Invoice ID to prevent "Duplicate Transaction" errors in Sandbox
        String invoiceId = "INV-" + java.util.UUID.randomUUID().toString();
        System.out.println("Invoice ID: " + invoiceId);
        
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .invoiceId(invoiceId) // Prevents TRANSACTION_REFUSED if blocking duplicate payments is on
                .amount(new AmountWithBreakdown()
                        .currencyCode("EUR") // Changed from USD to EUR
                        .value(totalAmount.setScale(2, java.math.RoundingMode.HALF_UP).toString()));
        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);

        OrdersCreateRequest request = new OrdersCreateRequest().requestBody(orderRequest);

        try {
            System.out.println("Sending request to PayPal...");
            var response = payPalHttpClient.execute(request);
            Order order = response.result();
            System.out.println("PayPal Order Created: " + order.id());
            System.out.println("Order Status: " + order.status());
            
            String approvalLink = order.links().stream()
                    .filter(link -> "approve".equals(link.rel()))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No approval link found"))
                    .href();
            
            System.out.println("Approval Link: " + approvalLink);
            System.out.println("=== PayPal Create Order SUCCESS ===");
            return approvalLink;
        } catch (IOException e) {
            System.err.println("=== PayPal Create Order FAILED (IOException) ===");
            System.err.println("Error Message: " + e.getMessage());
            System.err.println("Error Class: " + e.getClass().getName());
            e.printStackTrace();
            return null;
        } catch (NoSuchElementException e) {
            System.err.println("=== PayPal Create Order FAILED (No Approval Link) ===");
            System.err.println("Error Message: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) { // Catching all other exceptions
            System.err.println("=== PayPal Create Order FAILED (Unexpected Error) ===");
            System.err.println("Error Message: " + e.getMessage());
            System.err.println("Error Class: " + e.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

    public boolean captureOrder(String orderId) {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
        request.requestBody(new OrderRequest());

        try {
            var response = payPalHttpClient.execute(request);
            Order order = response.result();
            System.out.println("PayPal Capture Status: " + order.status()); // DEBUG
            return "COMPLETED".equals(order.status());
        } catch (IOException e) {
            System.err.println("PayPal Capture Failed: " + e.getMessage()); // DEBUG
            e.printStackTrace();
            return false;
        }
    }
    
    // Kept for compatibility if needed, but should not be used
    public boolean processPayPalPayment(String email, String password, BigDecimal amount) {
        return false;
    }
}
