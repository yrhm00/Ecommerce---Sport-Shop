package be.henallux.janvier.dataAccess.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_translations")
public class ProductTranslationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "locale", nullable = false, length = 5)
    private String locale;

    @Column(name = "nom_traduit", nullable = false, length = 150)
    private String nomTraduit;

    @Column(name = "description_traduite", columnDefinition = "TEXT")
    private String descriptionTraduite;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductEntity product;

    // Constructeurs
    public ProductTranslationEntity() {
    }

    public ProductTranslationEntity(Integer productId, String locale, String nomTraduit, String descriptionTraduite) {
        this.productId = productId;
        this.locale = locale;
        this.nomTraduit = nomTraduit;
        this.descriptionTraduite = descriptionTraduite;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getNomTraduit() {
        return nomTraduit;
    }

    public void setNomTraduit(String nomTraduit) {
        this.nomTraduit = nomTraduit;
    }

    public String getDescriptionTraduite() {
        return descriptionTraduite;
    }

    public void setDescriptionTraduite(String descriptionTraduite) {
        this.descriptionTraduite = descriptionTraduite;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}


