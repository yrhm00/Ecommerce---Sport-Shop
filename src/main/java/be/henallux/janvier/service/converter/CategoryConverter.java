package be.henallux.janvier.service.converter;

import be.henallux.janvier.dataAccess.entity.CategoryEntity;
import be.henallux.janvier.model.Category;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public Category categoryEntityToCategoryModel(CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setCode(categoryEntity.getCode());

        // Logique pour trouver la traduction
        String languageId = LocaleContextHolder.getLocale().getLanguage();
        categoryEntity.getTranslations().stream()
                .filter(t -> t.getLanguageId().equals(languageId))
                .findFirst()
                .ifPresentOrElse(
                        t -> category.setNom(t.getName()),
                        () -> category.setNom(categoryEntity.getCode()) // Fallback
                );
        category.setImageUrl(categoryEntity.getImageUrl());

        return category;
    }

    public CategoryEntity categoryModelToCategoryEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.getId());
        categoryEntity.setCode(category.getCode());
        // Les traductions sont complexes à mapper en retour depuis un seul "nom".
        // Habituellement on ne sauvegarde pas la Catégorie depuis le Modèle dans ce
        // flux, lecture seule.
        // Si on sauvegarde, on aurait besoin de plusieurs noms.
        return categoryEntity;
    }
}
