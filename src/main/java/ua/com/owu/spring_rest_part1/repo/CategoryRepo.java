package ua.com.owu.spring_rest_part1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.spring_rest_part1.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
