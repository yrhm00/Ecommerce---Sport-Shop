package be.henallux.janvier.dataAccess.dao;

import be.henallux.janvier.dataAccess.entity.CategoryEntity;
import be.henallux.janvier.dataAccess.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class CategoryDAO {

    private final CategoryRepository repository;

    @Autowired
    public CategoryDAO(CategoryRepository repository) {
        this.repository = repository;
    }

    public ArrayList<CategoryEntity> getAllCategories() {
        return new ArrayList<>(repository.findAll());
    }

    @SuppressWarnings("null")
    public CategoryEntity getCategoryById(Integer categoryId) {
        return repository.findById(categoryId).orElse(null);
    }
}
