package be.henallux.janvier.dataAccess.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "code")
    private String code;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "image")
    private String image;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ProductTranslationEntity> translations = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY) // Sizes might be separate or handled here
    private List<ProductSizeEntity> sizes = new ArrayList<>();

    public ProductEntity() {
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<ProductTranslationEntity> getTranslations() {
        return translations;
    }

    public void setTranslations(List<ProductTranslationEntity> translations) {
        this.translations = translations;
    }

    public List<ProductSizeEntity> getSizes() {
        return sizes;
    }

    public void setSizes(List<ProductSizeEntity> sizes) {
        this.sizes = sizes;
    }
}
