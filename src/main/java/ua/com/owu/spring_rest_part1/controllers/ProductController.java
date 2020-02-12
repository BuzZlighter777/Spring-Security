package ua.com.owu.spring_rest_part1.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.spring_rest_part1.models.Category;
import ua.com.owu.spring_rest_part1.models.Product;
import ua.com.owu.spring_rest_part1.repo.CategoryRepo;
import ua.com.owu.spring_rest_part1.repo.ProductRepo;
import ua.com.owu.spring_rest_part1.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    private ProductService productService;

    public ProductController(ProductRepo productRepo, CategoryRepo categoryRepo, @Qualifier("psi1")ProductService productService) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        Product product = productRepo.findAll().stream().filter(u -> u.getId() == id).findFirst().get();
        return product;
    }

    @GetMapping("/all")
    public List<Product> getRawProducts() {
        return productRepo.findAll();
    }

    @PostMapping("/save")
    public void addProduct(@RequestBody Product product) {
        productRepo.save(product);
    }

    @PostMapping("/join/{productId}/{categoryId}")
    public void joinProductToCategory(
            @PathVariable int productId,
            @PathVariable int categoryId
    ) {
        Product product = productRepo.findAll().stream().filter(u -> u.getId() == productId).findFirst().get();
        Category category = categoryRepo.findAll().stream().filter(u -> u.getId() == categoryId).findFirst().get();

        product.setCategory(category);
        productRepo.save(product);
    }

}
