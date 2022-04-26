package ua.project.java_advhomework.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;
import ua.project.java_advhomework.models.entity.PreviousCaptain;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"hibernateInitializer"})
public class DrownedRussianShipWithPreviousCaptainDTO {
    private String name;
    private int year;
    private int tonnage;
    private List<PreviousCaptainDTO> previousCaptains;

    public DrownedRussianShipWithPreviousCaptainDTO(DrownedRussianShip ship) {
        this.name = ship.getName();
        this.year = ship.getYear();
        this.tonnage = ship.getTonnage();
        List<PreviousCaptainDTO> captainDTOS = new ArrayList<>();
        List<PreviousCaptain> captains = new ArrayList<>(ship.getPreviousCaptains());
        for (PreviousCaptain captain : captains) {
            captainDTOS.add(new PreviousCaptainDTO(captain));
        }
        this.previousCaptains =  new ArrayList<>(captainDTOS);
    }
}
