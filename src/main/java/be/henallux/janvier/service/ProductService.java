package be.henallux.janvier.service;

import be.henallux.janvier.dataAccess.dao.ProductDAO;
import be.henallux.janvier.dataAccess.entity.ProductEntity;
import be.henallux.janvier.model.Product;
import be.henallux.janvier.service.converter.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    private final ProductDAO productDAO;
    private final ProductConverter productConverter;

    @Autowired
    public ProductService(ProductDAO productDAO, ProductConverter productConverter) {
        this.productDAO = productDAO;
        this.productConverter = productConverter;
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<ProductEntity> productEntities = productDAO.getAllProducts();
        ArrayList<Product> products = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            products.add(productConverter.productEntityToProductModel(productEntity));
        }
        return products;
    }

    public ArrayList<Product> getProductsByCategory(Integer categoryId) {
        ArrayList<ProductEntity> productEntities = productDAO.getProductsByCategoryId(categoryId);
        ArrayList<Product> products = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            products.add(productConverter.productEntityToProductModel(productEntity));
        }
        return products;
    }

    public Product getProductById(Integer id) {
        ProductEntity productEntity = productDAO.getProductById(id);
        return productConverter.productEntityToProductModel(productEntity);
    }

    public ArrayList<Product> getNewArrivals() {
        // Mock method - in real life, call specific DAO method
        return getAllProducts();
    }

    public ArrayList<Product> getPromotions() {
        // Mock method - in real life, call specific DAO method
        return getAllProducts();
    }
}
