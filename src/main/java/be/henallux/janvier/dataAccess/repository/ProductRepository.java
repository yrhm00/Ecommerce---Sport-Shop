package be.henallux.janvier.dataAccess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.henallux.janvier.dataAccess.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    
    // Trouver un produit par son code
    ProductEntity findByCode(String code);
    
    // Lister tous les produits d'une catégorie
    List<ProductEntity> findByCategoryId(Integer categoryId);
    
    // Lister tous les produits d'une catégorie triés par nom
    List<ProductEntity> findByCategoryIdOrderByNomAsc(Integer categoryId);

    // Trouver les 4 derniers produits ajoutés
    List<ProductEntity> findTop4ByOrderByCreatedAtDesc();

    // Trouver les produits dont le prix est supérieur à un certain montant
    List<ProductEntity> findByPrixGreaterThan(java.math.BigDecimal prix);
}


