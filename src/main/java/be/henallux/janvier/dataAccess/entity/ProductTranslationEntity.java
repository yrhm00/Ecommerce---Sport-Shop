package be.henallux.janvier.dataAccess.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_translations")
public class ProductTranslationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "language_id")
    private String languageId;

    @Column(name = "nom_traduit")
    private String name;

    @Column(name = "description_traduite")
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public ProductTranslationEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
