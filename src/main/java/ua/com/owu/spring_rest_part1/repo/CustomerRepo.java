package ua.com.owu.spring_rest_part1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.spring_rest_part1.models.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Customer findByUsername(String username);
}
