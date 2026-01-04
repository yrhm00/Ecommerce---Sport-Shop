package be.henallux.janvier.dataAccess.dao;

import be.henallux.janvier.dataAccess.entity.ProductEntity;
import be.henallux.janvier.dataAccess.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class ProductDAO {

    private final ProductRepository repository;

    @Autowired
    public ProductDAO(ProductRepository repository) {
        this.repository = repository;
    }

    public ArrayList<ProductEntity> getAllProducts() {
        return new ArrayList<>(repository.findAll());
    }

    @SuppressWarnings("null")
    public ProductEntity getProductById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public ArrayList<ProductEntity> getProductsByCategoryId(Integer categoryId) {
        // Attention : orderByNomAsc pourrait échouer si le champ 'nom' a disparu.
        // Idéalement utiliser
        // repository.findByCategoryId(categoryId)
        return new ArrayList<>(repository.findByCategoryId(categoryId));
    }

    public ArrayList<ProductEntity> getNewArrivals() {
        return new ArrayList<>(repository.findTop4ByOrderByCreatedAtDesc());
    }

    public ArrayList<ProductEntity> getPromotions() {
        return new ArrayList<>(repository.findByPriceGreaterThan(new java.math.BigDecimal("100.00")));
    }
}
