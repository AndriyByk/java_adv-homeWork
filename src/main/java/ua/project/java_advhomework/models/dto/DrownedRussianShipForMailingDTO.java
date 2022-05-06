package ua.project.java_advhomework.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;

@Data
//@JsonIgnoreProperties(value = {"hibernateInitializer"})
public class DrownedRussianShipForMailingDTO {
    private String picture;
    private String name;
    private String email;

    public DrownedRussianShipForMailingDTO(DrownedRussianShip ship) {
        this.picture = ship.getPicture();
        this.name = ship.getName();
        this.email = ship.getEmail();
    }
}
