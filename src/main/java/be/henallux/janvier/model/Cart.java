package be.henallux.janvier.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente le panier de l'utilisateur (stocké en session)
 */
public class Cart {

    private List<CartItem> items = new ArrayList<>();

    // Constructeurs
    public Cart() {
    }

    /**
     * Ajoute un produit au panier ou augmente sa quantité s'il existe déjà
     */
    public void addItem(Product product, Integer quantite) {
        if (product == null || quantite == null || quantite <= 0) {
            return;
        }

        // Chercher si le produit est déjà dans le panier
        CartItem existingItem = findItemByProductId(product.getId());
        
        if (existingItem != null) {
            // Augmenter la quantité
            existingItem.setQuantite(existingItem.getQuantite() + quantite);
        } else {
            // Ajouter un nouvel article
            items.add(new CartItem(product, quantite));
        }
    }

    /**
     * Modifie la quantité d'un produit dans le panier
     */
    public void updateQuantity(Integer productId, Integer quantite) {
        if (quantite == null || quantite <= 0) {
            removeItem(productId);
            return;
        }

        CartItem item = findItemByProductId(productId);
        if (item != null) {
            item.setQuantite(quantite);
        }
    }

    /**
     * Supprime un produit du panier
     */
    public void removeItem(Integer productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    /**
     * Vide complètement le panier
     */
    public void clear() {
        items.clear();
    }

    /**
     * Trouve un article par son productId
     */
    private CartItem findItemByProductId(Integer productId) {
        return items.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Calcule le total du panier
     */
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : items) {
            total = total.add(item.getSousTotal());
        }
        return total;
    }

    /**
     * Retourne le nombre total d'articles dans le panier
     */
    public int getTotalItems() {
        return items.stream()
                .mapToInt(CartItem::getQuantite)
                .sum();
    }

    // Getters et Setters
    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}


