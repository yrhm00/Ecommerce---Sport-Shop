package be.henallux.janvier.dataAccess.dao;

import be.henallux.janvier.dataAccess.entity.CategoryEntity;
import be.henallux.janvier.dataAccess.repository.CategoryRepository;
import be.henallux.janvier.dataAccess.util.ProviderConverter;
import be.henallux.janvier.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        for (CategoryEntity entity : entities) {
            categories.add(converter.categoryEntityToModel(entity));
        }
        return categories;
    }

    @Override
    public Category findById(Integer id) {
        CategoryEntity entity = repository.findById(id).orElse(null);
        return converter.categoryEntityToModel(entity);
    }

    @Override
    public Category findByCode(String code) {
        CategoryEntity entity = repository.findByCode(code);
        return converter.categoryEntityToModel(entity);
    }
}


