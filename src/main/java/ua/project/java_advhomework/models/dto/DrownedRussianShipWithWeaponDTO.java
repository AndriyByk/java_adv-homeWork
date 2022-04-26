package ua.project.java_advhomework.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;
import ua.project.java_advhomework.models.entity.Weapon;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"hibernateInitializer"})
public class DrownedRussianShipWithWeaponDTO {
    private String name;
    private int year;
    private int tonnage;
    private List<WeaponDTO> weapons;

    public DrownedRussianShipWithWeaponDTO(DrownedRussianShip ship) {
        this.name = ship.getName();
        this.year = ship.getYear();
        this.tonnage = ship.getTonnage();
        List<WeaponDTO> weaponDTOS = new ArrayList<>();
        List<Weapon> weapons = new ArrayList<>(ship.getWeapons());
        for (Weapon weapon : weapons) {
            weaponDTOS.add(new WeaponDTO(weapon));
        }
        this.weapons = new ArrayList<>(weaponDTOS);
    }
}
