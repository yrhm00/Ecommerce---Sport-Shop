package be.henallux.janvier.dataAccess.repository;

import be.henallux.janvier.dataAccess.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    // Trouver une catégorie par son code
    CategoryEntity findByCode(String code);

    // Lister toutes les catégories triées par code (nom n'existe plus dans
    // l'entité)
    List<CategoryEntity> findAllByOrderByCodeAsc();
}
