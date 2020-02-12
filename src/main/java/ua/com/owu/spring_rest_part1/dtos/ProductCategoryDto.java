package ua.com.owu.spring_rest_part1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.owu.spring_rest_part1.models.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {

    private int categoryId;
    private String categoryName;
    private List<Product> productList = new ArrayList<>();

}
