package ua.com.owu.spring_rest_part1.services;

import org.springframework.stereotype.Service;
import ua.com.owu.spring_rest_part1.models.Product;

import java.util.List;

@Service
public interface ProductService {

    void save(Product product);

    List<Product> findAll();

    Product findOneById(int productId);
}
