package ua.com.owu.spring_rest_part1.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Skill> skills = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public User(String name, List<Skill> skills) {
        this.name = name;
        this.skills = skills;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
