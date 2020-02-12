package ua.com.owu.spring_rest_part1.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.spring_rest_part1.dtos.ProductCategoryDto;
import ua.com.owu.spring_rest_part1.models.Category;
import ua.com.owu.spring_rest_part1.repo.CategoryRepo;


@Service
@AllArgsConstructor
public class ProductDtoService {

    @Autowired
    private CategoryRepo categoryRepo;

    public ProductCategoryDto getCategoryFullInfo(int categoryId) {
        Category category = categoryRepo.findAll().stream().filter(u -> u.getId() == categoryId).findFirst().get();

        ProductCategoryDto productCategoryDto = new ProductCategoryDto(
                category.getId(),
                category.getName(),
                category.getProductList()
        );
        return productCategoryDto;
    }
}
