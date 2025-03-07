package com.example.demoasm1java5.Service;

import com.example.demoasm1java5.Entity.Category;
import com.example.demoasm1java5.Entity.Product;
import com.example.demoasm1java5.Repository.CategoryRepository;
import com.example.demoasm1java5.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public List<Product> getFeaturedProducts() {
        return productRepository.findTop3ByOrderByIdAsc();
    }
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
    public List<Product> filterByPrice(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceRange(minPrice, maxPrice);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
