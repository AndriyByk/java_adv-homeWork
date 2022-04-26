package ua.project.java_advhomework.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;
import ua.project.java_advhomework.models.entity.PreviousCaptain;
import ua.project.java_advhomework.models.entity.Weapon;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"hibernateInitializer"})
public class DrownedRussianShipWithAllComponentsDTO {
    private String name;
    private int year;
    private int tonnage;
    private List<PreviousCaptainDTO> previousCaptains;
    private List<WeaponDTO> weapons;
    private CaptainDTO captains;

    public DrownedRussianShipWithAllComponentsDTO(DrownedRussianShip ship) {
        this.name = ship.getName();
        this.year = ship.getYear();
        this.tonnage = ship.getTonnage();

        List<PreviousCaptainDTO> captainDTOS = new ArrayList<>();
        List<PreviousCaptain> captains = new ArrayList<>(ship.getPreviousCaptains());
        for (PreviousCaptain captain : captains) {
            captainDTOS.add(new PreviousCaptainDTO(captain));
        }
        this.previousCaptains =  new ArrayList<>(captainDTOS);

        List<WeaponDTO> weaponDTOS = new ArrayList<>();
        List<Weapon> weapons = new ArrayList<>(ship.getWeapons());
        for (Weapon weapon : weapons) {
            weaponDTOS.add(new WeaponDTO(weapon));
        }
        this.weapons = new ArrayList<>(weaponDTOS);
        this.captains = new CaptainDTO(ship.getCaptain());
    }
}
