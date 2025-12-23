package be.henallux.janvier.dataAccess.dao;

import be.henallux.janvier.dataAccess.entity.ProductEntity;
import be.henallux.janvier.dataAccess.repository.ProductRepository;
import be.henallux.janvier.dataAccess.util.ProviderConverter;
import be.henallux.janvier.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductDAO implements ProductDataAccess {

    private final ProductRepository repository;
    private final ProviderConverter converter;

    @Autowired
    public ProductDAO(ProductRepository repository, ProviderConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> entities = repository.findAll();
        List<Product> products = new ArrayList<>();
        for (ProductEntity entity : entities) {
            products.add(converter.productEntityToModel(entity));
        }
        return products;
    }

    @Override
    public Product findById(Integer id) {
        ProductEntity entity = repository.findById(id).orElse(null);
        return converter.productEntityToModel(entity);
    }

    @Override
    public List<Product> findByCategoryId(Integer categoryId) {
        List<ProductEntity> entities = repository.findByCategoryIdOrderByNomAsc(categoryId);
        List<Product> products = new ArrayList<>();
        for (ProductEntity entity : entities) {
            products.add(converter.productEntityToModel(entity));
        }
        return products;
    }
}


