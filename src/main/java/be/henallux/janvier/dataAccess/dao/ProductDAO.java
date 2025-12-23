package be.henallux.janvier.dataAccess.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.henallux.janvier.dataAccess.entity.ProductEntity;
import be.henallux.janvier.dataAccess.repository.ProductRepository;
import be.henallux.janvier.dataAccess.util.ProviderConverter;
import be.henallux.janvier.model.Product;

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
        String language = org.springframework.context.i18n.LocaleContextHolder.getLocale().getLanguage();
        for (ProductEntity entity : entities) {
            products.add(converter.productEntityToModel(entity, language));
        }
        return products;
    }

    @Override
    public Product findById(Integer id) {
        ProductEntity entity = repository.findById(id).orElse(null);
        String language = org.springframework.context.i18n.LocaleContextHolder.getLocale().getLanguage();
        return converter.productEntityToModel(entity, language);
    }

    @Override
    public List<Product> findByCategoryId(Integer categoryId) {
        List<ProductEntity> entities = repository.findByCategoryIdOrderByNomAsc(categoryId);
        List<Product> products = new ArrayList<>();
        String language = org.springframework.context.i18n.LocaleContextHolder.getLocale().getLanguage();
        for (ProductEntity entity : entities) {
            products.add(converter.productEntityToModel(entity, language));
        }
        return products;
    }
}


