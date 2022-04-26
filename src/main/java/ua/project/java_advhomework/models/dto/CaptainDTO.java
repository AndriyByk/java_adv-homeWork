package ua.project.java_advhomework.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ua.project.java_advhomework.models.entity.Captain;

@Data
@JsonIgnoreProperties(value = {"hibernateInitializer"})
public class CaptainDTO {
    private String name;

    public CaptainDTO(Captain captain) {
        this.name = captain.getName();
    }
}