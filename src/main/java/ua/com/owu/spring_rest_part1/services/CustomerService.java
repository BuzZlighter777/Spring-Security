package ua.com.owu.spring_rest_part1.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.owu.spring_rest_part1.models.Customer;
import ua.com.owu.spring_rest_part1.models.Product;

import java.util.List;

public interface CustomerService extends UserDetailsService {

    void save(Customer customer);

    List<Customer> findAll();

    Customer findOneById(int productId);
}
