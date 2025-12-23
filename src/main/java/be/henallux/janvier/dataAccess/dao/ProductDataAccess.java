package be.henallux.janvier.dataAccess.dao;

import be.henallux.janvier.model.Product;

import java.util.List;

/**
 * Interface DataAccess pour les produits
 */
public interface ProductDataAccess {
    
    List<Product> findAll();
    
    Product findById(Integer id);
    
    List<Product> findByCategoryId(Integer categoryId);
}


