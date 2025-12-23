package be.henallux.janvier.dataAccess.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category_translations")
public class CategoryTranslationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "locale", nullable = false, length = 5)
    private String locale;

    @Column(name = "nom_traduit", nullable = false, length = 100)
    private String nomTraduit;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CategoryEntity category;

    // Constructeurs
    public CategoryTranslationEntity() {
    }

    public CategoryTranslationEntity(Integer categoryId, String locale, String nomTraduit) {
        this.categoryId = categoryId;
        this.locale = locale;
        this.nomTraduit = nomTraduit;
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}


