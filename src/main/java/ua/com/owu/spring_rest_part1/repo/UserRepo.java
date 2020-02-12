package ua.com.owu.spring_rest_part1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.spring_rest_part1.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByEmailAndPassword(String email, String password);

    User findByNameAndPassword(String name, String password);

}
