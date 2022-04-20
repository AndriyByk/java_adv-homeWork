package ua.project.java_advhomework.models.dto;

import ua.project.java_advhomework.models.entity.DrownedRussianShip;

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
