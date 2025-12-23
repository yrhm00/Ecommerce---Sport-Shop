package be.henallux.janvier.dataAccess.repository;

import be.henallux.janvier.dataAccess.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    
    // Trouver un produit par son code
    ProductEntity findByCode(String code);
    
    // Lister tous les produits d'une catégorie
    List<ProductEntity> findByCategoryId(Integer categoryId);
    
    // Lister tous les produits d'une catégorie triés par nom
    List<ProductEntity> findByCategoryIdOrderByNomAsc(Integer categoryId);
}


