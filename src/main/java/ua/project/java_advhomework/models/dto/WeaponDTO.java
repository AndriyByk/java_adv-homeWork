package ua.project.java_advhomework.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ua.project.java_advhomework.models.entity.Weapon;

@Data
@JsonIgnoreProperties(value = {"hibernateInitializer"})
public class WeaponDTO {
    private String code;

    public WeaponDTO(Weapon weapon) {
        this.code = weapon.getCode();
    }
}
