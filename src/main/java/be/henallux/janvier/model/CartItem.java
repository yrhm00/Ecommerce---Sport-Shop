package be.henallux.janvier.model;

import java.math.BigDecimal;

/**
 * Représente un article dans le panier de l'utilisateur (session)
 */
public class CartItem {

    private Product product;
    private Integer quantite;
    private String taille; // Taille selectionnée (peut être null si pas de taille)

    // Constructeurs
    public CartItem() {
    }

    public CartItem(Product product, Integer quantite, String taille) {
        this.product = product;
        this.quantite = quantite;
        this.taille = taille;
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

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }
}


