package be.henallux.janvier.service.converter;

import be.henallux.janvier.dataAccess.entity.ProductEntity;
import be.henallux.janvier.model.Product;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product productEntityToProductModel(ProductEntity productEntity) {
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setCategoryId(productEntity.getCategoryId());
        product.setCode(productEntity.getCode());
        product.setPrix(productEntity.getPrice());
        product.setStock(productEntity.getStock());
        product.setImageUrl(productEntity.getImage());
        product.setCreatedAt(productEntity.getCreatedAt());

        String languageId = LocaleContextHolder.getLocale().getLanguage();
        productEntity.getTranslations().stream()
                .filter(t -> t.getLanguageId().equals(languageId))
                .findFirst()
                .ifPresentOrElse(
                        t -> {
                            product.setNom(t.getName());
                            product.setDescription(t.getDescription());
                        },
                        () -> {
                            product.setNom(productEntity.getCode()); // Fallback
                            product.setDescription("");
                        });

        return product;
    }

    public ProductEntity productModelToProductEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setCategoryId(product.getCategoryId());
        productEntity.setCode(product.getCode());
        productEntity.setPrice(product.getPrix());
        productEntity.setStock(product.getStock());
        productEntity.setImage(product.getImageUrl());
        productEntity.setCreatedAt(product.getCreatedAt());
        return productEntity;
    }
}
