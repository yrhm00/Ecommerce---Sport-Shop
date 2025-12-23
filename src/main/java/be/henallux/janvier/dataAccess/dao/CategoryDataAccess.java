package be.henallux.janvier.dataAccess.dao;

import be.henallux.janvier.model.Category;

import java.util.List;

/**
 * Interface DataAccess pour les catégories
 */
public interface CategoryDataAccess {
    
    List<Category> findAll();
    
    Category findById(Integer id);
    
    Category findByCode(String code);
}


