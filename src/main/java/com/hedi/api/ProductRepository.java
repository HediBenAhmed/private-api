package com.hedi.api;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findById(long id);
    List<Product> findByCategory(String category);
    List<Product> findByTitleContainingIgnoreCase(String titleLike);
    List<Product> findByCategoryAndTitleContainingIgnoreCase(String category,String titleLike);
}
