package ua.com.owu.spring_rest_part1.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.owu.spring_rest_part1.models.Product;
import ua.com.owu.spring_rest_part1.repo.ProductRepo;

import java.util.List;

@Service("psi2")
@AllArgsConstructor
public class ProductServiceImpl2 implements ProductService {

    private ProductRepo productRepo;

    @Override
    public void save(Product product) {
        if (product != null) productRepo.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findOneById(int productId) {
        return productRepo.findById(productId).get();
    }
}
