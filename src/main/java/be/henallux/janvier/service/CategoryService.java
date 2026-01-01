package be.henallux.janvier.service;

import be.henallux.janvier.dataAccess.dao.CategoryDAO;
import be.henallux.janvier.dataAccess.entity.CategoryEntity;
import be.henallux.janvier.model.Category;
import be.henallux.janvier.service.converter.CategoryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    private final CategoryDAO categoryDAO;
    private final CategoryConverter categoryConverter;

    @Autowired
    public CategoryService(CategoryDAO categoryDAO, CategoryConverter categoryConverter) {
        this.categoryDAO = categoryDAO;
        this.categoryConverter = categoryConverter;
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<CategoryEntity> categoryEntities = categoryDAO.getAllCategories();
        ArrayList<Category> categories = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            categories.add(categoryConverter.categoryEntityToCategoryModel(categoryEntity));
        }
        return categories;
    }

    public Category getCategoryById(Integer id) {
        CategoryEntity categoryEntity = categoryDAO.getCategoryById(id);
        return categoryConverter.categoryEntityToCategoryModel(categoryEntity);
    }
}
