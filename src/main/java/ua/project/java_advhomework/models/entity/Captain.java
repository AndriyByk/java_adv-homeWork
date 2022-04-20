package ua.project.java_advhomework.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Captain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ToString.Exclude
    @OneToOne(mappedBy = "captain", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DrownedRussianShip drownedRussianShip;

}
