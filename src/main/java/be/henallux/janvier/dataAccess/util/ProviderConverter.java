package be.henallux.janvier.dataAccess.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import be.henallux.janvier.dataAccess.entity.AuthorityEntity;
import be.henallux.janvier.dataAccess.entity.CategoryEntity;
import be.henallux.janvier.dataAccess.entity.CategoryTranslationEntity;
import be.henallux.janvier.dataAccess.entity.ProductEntity;
import be.henallux.janvier.dataAccess.entity.ProductSizeEntity;
import be.henallux.janvier.dataAccess.entity.ProductTranslationEntity;
import be.henallux.janvier.dataAccess.entity.UserEntity;
import be.henallux.janvier.model.Authority;
import be.henallux.janvier.model.Category;
import be.henallux.janvier.model.Product;
import be.henallux.janvier.model.User;

@Component
public class ProviderConverter {

    private final Mapper mapper;

    public ProviderConverter() {
        this.mapper = DozerBeanMapperBuilder.buildDefault();
    }

    // ========== USER ==========
    public User userEntityToModel(UserEntity entity) {
        if (entity == null) return null;
        
        try {
            User model = mapper.map(entity, User.class);
            
            // Conversion des authorities
            Set<Authority> authorities = new HashSet<>();
            if (entity.getAuthorities() != null) {
                for (AuthorityEntity authEntity : entity.getAuthorities()) {
                    authorities.add(new Authority(authEntity.getAuthority()));
                }
            }
            model.setAuthorities(authorities);
            
            return model;
        } catch (Exception e) {
            System.err.println("Error mapping UserEntity to User: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public UserEntity userModelToEntity(User model) {
        if (model == null) return null;
        UserEntity entity = mapper.map(model, UserEntity.class);
        
       
        entity.setAuthorities(null);
        
        return entity;
    }

   
    public Authority authorityEntityToModel(AuthorityEntity entity) {
        if (entity == null) return null;
        return new Authority(entity.getAuthority());
    }

    public AuthorityEntity authorityModelToEntity(Authority model, String username) {
        if (model == null) return null;
        return new AuthorityEntity(username, model.getAuthority());
    }

   
    public Category categoryEntityToModel(CategoryEntity entity) {
        return categoryEntityToModel(entity, null);
    }

    public Category categoryEntityToModel(CategoryEntity entity, String language) {
        if (entity == null) return null;
        Category model = mapper.map(entity, Category.class);
        
        // Gestion de la traduction
        if (language != null && entity.getTranslations() != null) {
            for (CategoryTranslationEntity translation : entity.getTranslations()) {
                if (translation.getLocale().equals(language)) {
                    model.setNom(translation.getNomTraduit());
                    break;
                }
            }
        }
        
        return model;
    }

    public CategoryEntity categoryModelToEntity(Category model) {
        if (model == null) return null;
        return mapper.map(model, CategoryEntity.class);
    }

    
    public Product productEntityToModel(ProductEntity entity) {
        return productEntityToModel(entity, null);
    }

    public Product productEntityToModel(ProductEntity entity, String language) {
        if (entity == null) return null;
        Product model = mapper.map(entity, Product.class);
        
        // Gestion de la traduction
        if (language != null && entity.getTranslations() != null) {
            for (ProductTranslationEntity translation : entity.getTranslations()) {
                if (translation.getLocale().equals(language)) {
                    model.setNom(translation.getNomTraduit());
                    model.setDescription(translation.getDescriptionTraduite());
                    break;
                }
            }
        }
        
        // Ajouter le nom de la catégorie si disponible
        if (entity.getCategory() != null) {
            // Idéalement on traduirait aussi le nom de la catégorie ici, mais cela demanderait plus de logique
            model.setCategoryNom(entity.getCategory().getNom());
        }

        // Conversion des tailles et stocks
        if (entity.getSizes() != null && !entity.getSizes().isEmpty()) {
            Map<String, Integer> sizesStock = new HashMap<>();
            int totalStock = 0;
            for (ProductSizeEntity sizeEntity : entity.getSizes()) {
                sizesStock.put(sizeEntity.getTaille(), sizeEntity.getStock());
                totalStock += sizeEntity.getStock();
            }
            model.setSizesStock(sizesStock);
            // On met à jour le stock total affiché pour correspondre à la somme des variantes
            model.setStock(totalStock);
        }
        
        return model;
    }

    public ProductEntity productModelToEntity(Product model) {
        if (model == null) return null;
        return mapper.map(model, ProductEntity.class);
    }
}


