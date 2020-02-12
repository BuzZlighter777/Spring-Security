package ua.com.owu.spring_rest_part1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.com.owu.spring_rest_part1.models.Customer;
import ua.com.owu.spring_rest_part1.models.Product;
import ua.com.owu.spring_rest_part1.repo.CustomerRepo;
import ua.com.owu.spring_rest_part1.repo.ProductRepo;


@Controller
@AllArgsConstructor
public class LayoutController {

    private ProductRepo productRepo;
    private CustomerRepo customerRepo;

    @GetMapping("/html-products")
    public String getRawProducts(Model model) {
        model.addAttribute("productList", productRepo.findAll());
        return "products";
    }

    @GetMapping("/buy-product-{productId}")
    public String buyProduct(@PathVariable int productId) {
        Product product = productRepo.findAll().stream().filter(u -> u.getId() == productId).findFirst().get();
        Customer customer = customerRepo.findAll().stream().findFirst().get(); // only one constant customer for purchases
        customer.getProductBin().add(product);
        product.getCustomerList().add(customer);

        productRepo.save(product);

        return "redirect:/html-products";
    }
}
