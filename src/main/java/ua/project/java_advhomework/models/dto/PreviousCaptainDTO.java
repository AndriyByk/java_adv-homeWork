package ua.project.java_advhomework.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ua.project.java_advhomework.models.entity.PreviousCaptain;

@Data
@JsonIgnoreProperties(value = {"hibernateInitializer"})
public class PreviousCaptainDTO {
    private String name;

    public PreviousCaptainDTO(PreviousCaptain previousCaptain) {
        this.name = previousCaptain.getName();
    }
}
