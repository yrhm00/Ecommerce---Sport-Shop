package be.henallux.janvier.model;

import java.math.BigDecimal;

/**
 * Représente un article dans le panier de l'utilisateur (session)
 */
public class CartItem {

    private Product product;
    private Integer quantite;

    // Constructeurs
    public CartItem() {
    }

    public CartItem(Product product, Integer quantite) {
        this.product = product;
        this.quantite = quantite;
    }

    // Méthode pour calculer le sous-total de cette ligne
    public BigDecimal getSousTotal() {
        if (product != null && product.getPrix() != null && quantite != null) {
            return product.getPrix().multiply(BigDecimal.valueOf(quantite));
        }
        return BigDecimal.ZERO;
    }

    // Getters et Setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}


