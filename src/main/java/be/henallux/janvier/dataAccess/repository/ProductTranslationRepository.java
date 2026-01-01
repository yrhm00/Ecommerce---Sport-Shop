package be.henallux.janvier.dataAccess.repository;

import be.henallux.janvier.dataAccess.entity.ProductTranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTranslationRepository extends JpaRepository<ProductTranslationEntity, Integer> {

    // Trouver les traductions d'un produit
    List<ProductTranslationEntity> findByProductId(Integer productId);

    // Trouver une traduction spécifique
    ProductTranslationEntity findByProductIdAndLanguageId(Integer productId, String languageId);
}
