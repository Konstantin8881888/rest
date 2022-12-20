package com.example.rest;

import com.example.rest.repository.ProductRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController
{
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getAllProduct()
    {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getAllProduct(@PathVariable Long id)
    {
        return productRepository.findById(id).orElseThrow();
    }

    @PostMapping("/products")
    public Product saveNewProduct(@RequestBody Product product)
    {
        product.setId(null);
        return productRepository.save(product);
    }

    @DeleteMapping("/products/delete/{id}")
    public void deleteById(@PathVariable Long id)
    {
        productRepository.deleteById(id);
    }

    @DeleteMapping("/products/delete")
    public void deleteById()
    {
        productRepository.deleteAll();
    }

    @PutMapping("/products")
    public Product updateStudent(@RequestBody Product product)
    {
        return productRepository.save(product);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void create()
    {
        productRepository.save(createNewProduct("apple"));
        productRepository.save(createNewProduct("orange"));
        productRepository.save(createNewProduct("grape"));
        productRepository.save(createNewProduct("raspberry"));
        productRepository.save(createNewProduct("melon"));
        productRepository.save(createNewProduct("watermelon"));
    }

    private Product createNewProduct(String title)
    {
        Product product = new Product();
        product.setTitle(title);
        return product;
    }
}
