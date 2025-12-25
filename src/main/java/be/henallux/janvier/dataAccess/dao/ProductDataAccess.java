package be.henallux.janvier.dataAccess.dao;

import java.util.List;

import be.henallux.janvier.model.Product;


public interface ProductDataAccess {
    
    List<Product> findAll();
    
    Product findById(Integer id);
    
    List<Product> findByCategoryId(Integer categoryId);

    List<Product> findNewArrivals();

    List<Product> findPromotions();
}


