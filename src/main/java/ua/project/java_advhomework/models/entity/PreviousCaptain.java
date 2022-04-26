package ua.project.java_advhomework.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PreviousCaptain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int durationOfService;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private DrownedRussianShip drownedRussianShip;
}
