package ua.project.java_advhomework.models.entity.tokens;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.project.java_advhomework.models.entity.users.User;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String payload;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
}
