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
        products.forEach(this::applyPromotions);
        return products;
    }

    public List<Product> getProductsByCategory(Integer categoryId) {
        List<Product> products = productDAO.findByCategoryId(categoryId);
        products.forEach(this::applyPromotions);
        return products;
    }

    public Product getProductById(Integer id) {
        Product product = productDAO.findById(id);
        if (product != null) {
            applyPromotions(product);
        }
        return product;
    }

    /**
     * Applique une promotion de 10% si le prix est supérieur à 100€
     */
    private void applyPromotions(Product product) {
        if (product.getPrix() != null && product.getPrix().doubleValue() > 100.0) {
            // Logique simple : 10% de réduction pour les articles chers
            // En réalité, on pourrait stocker les règles de promo en DB
            double newPrice = product.getPrix().doubleValue() * 0.9;
            // Arrondir à 2 décimales
            newPrice = Math.round(newPrice * 100.0) / 100.0;
            product.setPrix(java.math.BigDecimal.valueOf(newPrice));
            // On pourrait ajouter un champ "originalPrice" au modèle pour afficher le prix barré
        }
    }
}
