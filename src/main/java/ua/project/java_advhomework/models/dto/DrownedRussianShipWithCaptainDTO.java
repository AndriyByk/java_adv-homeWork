package ua.project.java_advhomework.models.dto;

import lombok.Data;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;

@Data
public class DrownedRussianShipWithCaptainDTO {
    private String name;
    private int year;
    private int tonnage;
    private CaptainDTO captainDTO;

    public DrownedRussianShipWithCaptainDTO(DrownedRussianShip ship) {
        this.name = ship.getName();
        this.year = ship.getYear();
        this.tonnage = ship.getTonnage();
        this.captainDTO = new CaptainDTO(ship.getCaptain());
    }
}
