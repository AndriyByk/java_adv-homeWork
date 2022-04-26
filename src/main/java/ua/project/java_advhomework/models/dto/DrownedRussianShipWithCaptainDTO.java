package ua.project.java_advhomework.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;

@Data
@JsonIgnoreProperties(value = {"hibernateInitializer"})
public class DrownedRussianShipWithCaptainDTO {
    private String name;
    private int year;
    private int tonnage;
    private CaptainDTO captain;

    public DrownedRussianShipWithCaptainDTO(DrownedRussianShip ship) {
        this.name = ship.getName();
        this.year = ship.getYear();
        this.tonnage = ship.getTonnage();
        this.captain = new CaptainDTO(ship.getCaptain());
    }
}
