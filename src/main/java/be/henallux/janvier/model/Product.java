package be.henallux.janvier.model;

import java.math.BigDecimal;

public class Product {

    private Integer id;
    private Integer categoryId;
    private String code;
    private String nom;
    private String description;
    private BigDecimal prix;
    private Integer stock;
    private String imageUrl;
    private String categoryNom; // Pour l'affichage

    // Constructeurs
    public Product() {
    }

    public Product(Integer categoryId, String code, String nom, String description, BigDecimal prix, Integer stock) {
        this.categoryId = categoryId;
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategoryNom() {
        return categoryNom;
    }

    public void setCategoryNom(String categoryNom) {
        this.categoryNom = categoryNom;
    }
}


