package be.henallux.janvier.dataAccess.repository;

import be.henallux.janvier.dataAccess.entity.CategoryTranslationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryTranslationRepository extends JpaRepository<CategoryTranslationEntity, Integer> {

    // Trouver les traductions d'une catégorie
    List<CategoryTranslationEntity> findByCategoryId(Integer categoryId);

    // Trouver une traduction spécifique
    CategoryTranslationEntity findByCategoryIdAndLanguageId(Integer categoryId, String languageId);
}
