package ua.project.java_advhomework.models.dto;

import lombok.Data;
import ua.project.java_advhomework.models.entity.Captain;

@Data
public class CaptainDTO {
    private String name;

    public CaptainDTO(Captain captain) {
        this.name = captain.getName();
    }
}
