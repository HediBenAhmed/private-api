package com.hedi.api;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository productRepository;


    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping("/products")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @RequestMapping("/products/{id}")
    public Product findOne(@PathVariable("id") long id) {
        return productRepository.findById(id);
    }

    @RequestMapping(value = "/products/search", method = RequestMethod.POST)
    public List<Product> search(@RequestBody SearchQueryDto query) {

        if (ObjectUtils.isEmpty(query.category) && ObjectUtils.isEmpty(query.titleLike)) {
            return productRepository.findAll();
        }

        if (ObjectUtils.isEmpty(query.category)) {
            return productRepository.findByTitleContainingIgnoreCase(query.titleLike);
        }

        if (ObjectUtils.isEmpty(query.titleLike)) {
            return productRepository.findByCategory(query.category);
        }
        return productRepository.findByCategoryAndTitleContainingIgnoreCase(query.category, query.titleLike);
    }


    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product save(Product product) {
        return productRepository.insert(product);
    }
}
