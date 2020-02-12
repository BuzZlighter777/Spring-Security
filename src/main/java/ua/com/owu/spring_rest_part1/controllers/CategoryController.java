package ua.com.owu.spring_rest_part1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.spring_rest_part1.dtos.ProductCategoryDto;
import ua.com.owu.spring_rest_part1.models.Category;
import ua.com.owu.spring_rest_part1.repo.CategoryRepo;
import ua.com.owu.spring_rest_part1.services.ProductDtoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryRepo categoryRepo;
    private ProductDtoService productDtoService;

    @GetMapping("/full-list")
    public List<ProductCategoryDto> getFullCategories() {
        List<ProductCategoryDto> productCategoryDtoList = categoryRepo.findAll()
                .stream()
                .map(category -> productDtoService.getCategoryFullInfo(category.getId()))
                .collect(Collectors.toList());
        return productCategoryDtoList;
    }

    @GetMapping("/all")
    public List<Category> getRawCategories() {
        return categoryRepo.findAll();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        Category category = categoryRepo.findAll().stream().filter(u -> u.getId() == id).findFirst().get();
        System.out.println(category);
        return category;
    }

    @PostMapping("/add/category")
    public void addCategory(@RequestBody Category category) {
        categoryRepo.save(category);
    }

}
