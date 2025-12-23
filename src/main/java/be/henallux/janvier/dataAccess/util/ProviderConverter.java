package be.henallux.janvier.dataAccess.util;

import be.henallux.janvier.dataAccess.entity.*;
import be.henallux.janvier.model.*;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProviderConverter {

    private final Mapper mapper;

    public ProviderConverter() {
        this.mapper = DozerBeanMapperBuilder.buildDefault();
    }

    // ========== USER ==========
    public User userEntityToModel(UserEntity entity) {
        if (entity == null) return null;
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
    }

    public UserEntity userModelToEntity(User model) {
        if (model == null) return null;
        return mapper.map(model, UserEntity.class);
    }

    // ========== AUTHORITY ==========
    public Authority authorityEntityToModel(AuthorityEntity entity) {
        if (entity == null) return null;
        return new Authority(entity.getAuthority());
    }

    public AuthorityEntity authorityModelToEntity(Authority model, String username) {
        if (model == null) return null;
        return new AuthorityEntity(username, model.getAuthority());
    }

    // ========== CATEGORY ==========
    public Category categoryEntityToModel(CategoryEntity entity) {
        if (entity == null) return null;
        return mapper.map(entity, Category.class);
    }

    public CategoryEntity categoryModelToEntity(Category model) {
        if (model == null) return null;
        return mapper.map(model, CategoryEntity.class);
    }

    // ========== PRODUCT ==========
    public Product productEntityToModel(ProductEntity entity) {
        if (entity == null) return null;
        Product model = mapper.map(entity, Product.class);
        
        // Ajouter le nom de la catégorie si disponible
        if (entity.getCategory() != null) {
            model.setCategoryNom(entity.getCategory().getNom());
        }
        
        return model;
    }

    public ProductEntity productModelToEntity(Product model) {
        if (model == null) return null;
        return mapper.map(model, ProductEntity.class);
    }
}


