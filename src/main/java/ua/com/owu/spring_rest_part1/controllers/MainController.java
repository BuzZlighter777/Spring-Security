package ua.com.owu.spring_rest_part1.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.spring_rest_part1.models.*;
import ua.com.owu.spring_rest_part1.repo.*;
import ua.com.owu.spring_rest_part1.services.CustomerService;
import ua.com.owu.spring_rest_part1.services.EmailService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

    private UserRepo userRepo;
    private SkillRepo skillRepo;
    private CustomerRepo customerRepo;

    private EmailService emailService;
    private CustomerService customerService;

    private PasswordEncoder passwordEncoder;

    @PostMapping("/add-customer")
    public List<Customer> addCustomers(@RequestBody Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.save(customer);
        return customerService.findAll();
    }

    @GetMapping("/customers")
    public List<Customer> showCustomers() { return customerRepo.findAll(); }

    @PostMapping("/user/{name}/{skills}")
    public void saveUser(
            @PathVariable String name,
            @PathVariable String skills) {

        String[] split = skills.split("_");
        List<Skill> skillList = Stream.of(split)
                .map(s -> Skill.builder().title(s).build())
                .collect(Collectors.toList());

        User user = new User(name);
        userRepo.save(user);

        skillList.forEach(skill -> {
            skill.setUser(user);
            skillRepo.save(skill);
        });

    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        if (user.getEmail().equals("")) return null;
        userRepo.save(user);
        user.getSkills().forEach(skill -> {
            skill.setUser(user);
            skillRepo.save(skill);
        });

        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        User dataUser = userRepo.findByNameAndPassword(user.getName(), user.getPassword());
        if (dataUser != null){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Authorization", "sed#$@%$ERAS");
            return ResponseEntity.ok().headers(responseHeaders).body("Good!");
        }
        return ResponseEntity.badRequest().body("Error!");
    }

    @GetMapping("/send-to-{id}")
    public void sendEmailToUser(@PathVariable int id) {
        User user = userRepo.findAll().stream().filter(u -> u.getId() == id).findFirst().get();
        emailService.send(user);
    }
}
