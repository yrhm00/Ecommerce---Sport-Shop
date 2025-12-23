package be.henallux.janvier.dataAccess.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.henallux.janvier.dataAccess.entity.CategoryEntity;
import be.henallux.janvier.dataAccess.repository.CategoryRepository;
import be.henallux.janvier.dataAccess.util.ProviderConverter;
import be.henallux.janvier.model.Category;

@Service
@Transactional
public class CategoryDAO implements CategoryDataAccess {

    private final CategoryRepository repository;
    private final ProviderConverter converter;

    @Autowired
    public CategoryDAO(CategoryRepository repository, ProviderConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<Category> findAll() {
        List<CategoryEntity> entities = repository.findAllByOrderByNomAsc();
        List<Category> categories = new ArrayList<>();
        String language = org.springframework.context.i18n.LocaleContextHolder.getLocale().getLanguage();
        for (CategoryEntity entity : entities) {
            categories.add(converter.categoryEntityToModel(entity, language));
        }
        return categories;
    }

    @Override
    public Category findById(Integer id) {
        CategoryEntity entity = repository.findById(id).orElse(null);
        String language = org.springframework.context.i18n.LocaleContextHolder.getLocale().getLanguage();
        return converter.categoryEntityToModel(entity, language);
    }

    @Override
    public Category findByCode(String code) {
        CategoryEntity entity = repository.findByCode(code);
        String language = org.springframework.context.i18n.LocaleContextHolder.getLocale().getLanguage();
        return converter.categoryEntityToModel(entity, language);
    }
}


