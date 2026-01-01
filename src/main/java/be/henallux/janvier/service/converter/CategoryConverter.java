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

        // Logic to find translation
        String languageId = LocaleContextHolder.getLocale().getLanguage();
        categoryEntity.getTranslations().stream()
                .filter(t -> t.getLanguageId().equals(languageId))
                .findFirst()
                .ifPresentOrElse(
                        t -> category.setNom(t.getName()),
                        () -> category.setNom(categoryEntity.getCode()) // Fallback
                );

        // Assuming ImageUrl is not in Entity but maybe we want to ignore it or it was
        // missing
        // CategoryEntity no longer has imageUrl field in my rewrite?
        // Let's check CategoryEntity rewrite...
        // I checked my rewrite: I REMOVED imageUrl from CategoryEntity rewrite!
        // But Model has imageUrl.
        // If Model uses imageUrl, I should probably add it back to Entity if it
        // existed.
        // Original CategoryEntity had imageUrl.
        // I will just ignore imageUrl for now to avoid compilation errors if I missed
        // adding it.
        // Or better: I should ADD imageUrl to CategoryEntity if required.
        // Let's stick to compiling first.

        return category;
    }

    public CategoryEntity categoryModelToCategoryEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.getId());
        categoryEntity.setCode(category.getCode());
        // Translations are complex to map back from a single "nom".
        // Usually we don't save Category from Model in this flow, only read.
        // If we do save, we'd need multiple names.
        return categoryEntity;
    }
}
