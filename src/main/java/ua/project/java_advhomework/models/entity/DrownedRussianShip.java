package ua.project.java_advhomework.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity

public class DrownedRussianShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int year;
    private int tonnage;
    private Type type;

    private String picture;
    private String email;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public DrownedRussianShip(String picture, String name, String email) {
        this.picture = picture;
        this.name = name;
        this.email = email;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public DrownedRussianShip(String picture, String name, int year, int tonnage) {
        this.picture = picture;
        this.name = name;
        this.year = year;
        this.tonnage = tonnage;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Captain captain;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<PreviousCaptain> previousCaptains;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "ship_weapon",
            joinColumns = @JoinColumn(name = "ship_id"),
            inverseJoinColumns = @JoinColumn(name = "weapon_id")
    )
    @ToString.Exclude
    private List<Weapon> weapons;
}
