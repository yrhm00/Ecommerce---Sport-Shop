package be.henallux.janvier.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.henallux.janvier.dataAccess.entity.OrderEntity;
import be.henallux.janvier.dataAccess.entity.OrderLineEntity;
import be.henallux.janvier.dataAccess.entity.UserEntity;
import be.henallux.janvier.dataAccess.repository.OrderLineRepository;
import be.henallux.janvier.dataAccess.repository.OrderRepository;
import be.henallux.janvier.dataAccess.repository.UserRepository;
import be.henallux.janvier.model.Cart;
import be.henallux.janvier.model.CartItem;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderLineRepository orderLineRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createOrder(Cart cart, String username, boolean isPaid) {
        if (cart == null || cart.getItems().isEmpty()) {
            return;
        }

        // Récupérer l'utilisateur
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            // Dans une vraie app, on pourrait gérer ceci mieux
            return;
        }

        // Créer la commande
        OrderEntity order = new OrderEntity();
        order.setDateCommande(LocalDateTime.now());
        order.setUserId(user.getId());
        order.setPaye(isPaid);
        
        // Calculer le total (en prenant en compte la réduction du panier)
        BigDecimal total = cart.getTotalWithDiscount();
        order.setMontantTotal(total);

        order = orderRepository.save(order);

        // Sauvegarder les lignes
        for (CartItem item : cart.getItems()) {
            OrderLineEntity line = new OrderLineEntity();
            line.setOrder(order);
            line.setProductId(item.getProduct().getId());
            line.setQuantite(item.getQuantite());
            
            // Gestion du prix (avec promotion potentielle)
            BigDecimal realPrice = item.getProduct().getPrix(); 
            if (realPrice == null) {
                realPrice = BigDecimal.ZERO;
            }
            line.setPrixUnitaire(realPrice);
            
            orderLineRepository.save(line);
        }

        // Vider le panier
        cart.clear();
    }
}
