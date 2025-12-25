package be.henallux.janvier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.henallux.janvier.dataAccess.dao.ProductDataAccess;
import be.henallux.janvier.model.Product;

@Service
public class ProductService {

    private final ProductDataAccess productDAO;

    @Autowired
    public ProductService(ProductDataAccess productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() {
        List<Product> products = productDAO.findAll();
        java.util.Set<Integer> newProductIds = getNewProductIds();
        products.forEach(p -> applyPromotionsAndMarkNew(p, newProductIds));
        return products;
    }

    public List<Product> getProductsByCategory(Integer categoryId) {
        List<Product> products = productDAO.findByCategoryId(categoryId);
        java.util.Set<Integer> newProductIds = getNewProductIds();
        products.forEach(p -> applyPromotionsAndMarkNew(p, newProductIds));
        return products;
    }

    public Product getProductById(Integer id) {
        Product product = productDAO.findById(id);
        if (product != null) {
            java.util.Set<Integer> newProductIds = getNewProductIds();
            applyPromotionsAndMarkNew(product, newProductIds);
        }
        return product;
    }

    public List<Product> getNewArrivals() {
        List<Product> products = productDAO.findNewArrivals();
        java.util.Set<Integer> newProductIds = getNewProductIds();
        products.forEach(p -> applyPromotionsAndMarkNew(p, newProductIds));
        return products;
    }

    public List<Product> getPromotions() {
        List<Product> products = productDAO.findPromotions();
        java.util.Set<Integer> newProductIds = getNewProductIds();
        products.forEach(p -> applyPromotionsAndMarkNew(p, newProductIds));
        return products;
    }


    /**
     * Récupère les IDs des produits considérés comme "nouveaux"
     */
    private java.util.Set<Integer> getNewProductIds() {
        List<Product> newArrivals = productDAO.findNewArrivals();
        return newArrivals.stream()
            .map(Product::getId)
            .collect(java.util.stream.Collectors.toSet());
    }

    /**
     * Applique les promotions et marque les produits nouveaux
     */
    private void applyPromotionsAndMarkNew(Product product, java.util.Set<Integer> newProductIds) {
        // Marquer comme nouveau si dans la liste des nouveautés
        if (newProductIds.contains(product.getId())) {
            product.setNewArrival(true);
        }
        
        // Appliquer les promotions
        if (product.getPrix() != null && product.getPrix().doubleValue() > 100.0) {
            product.setOriginalPrice(product.getPrix());
            double newPrice = product.getPrix().doubleValue() * 0.9;
            newPrice = Math.round(newPrice * 100.0) / 100.0;
            product.setPrix(java.math.BigDecimal.valueOf(newPrice));
        }
    }
}
