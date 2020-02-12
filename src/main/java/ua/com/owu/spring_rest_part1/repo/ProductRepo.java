package ua.com.owu.spring_rest_part1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.spring_rest_part1.models.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
