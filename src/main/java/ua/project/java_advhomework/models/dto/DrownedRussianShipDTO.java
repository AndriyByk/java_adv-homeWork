package ua.project.java_advhomework.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;

@Data
@JsonIgnoreProperties(value = {"hibernateInitializer"})
public class DrownedRussianShipDTO {
    private String name;
    private int year;
    private int tonnage;

    public DrownedRussianShipDTO(DrownedRussianShip drownedRussianShip) {
        this.name = drownedRussianShip.getName();
        this.year = drownedRussianShip.getYear();
        this.tonnage = drownedRussianShip.getTonnage();
    }
}
