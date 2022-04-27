package ua.project.java_advhomework.services;

import org.springframework.http.ResponseEntity;
import ua.project.java_advhomework.models.dto.WeaponDTO;
import ua.project.java_advhomework.models.entity.Weapon;

import java.util.List;

public interface IWeaponService {
    ResponseEntity<List<WeaponDTO>> findAllWeapons();
    ResponseEntity<WeaponDTO> findWeaponById(int id);
    ResponseEntity<List<WeaponDTO>> findByCode (String code);
    ResponseEntity<WeaponDTO> updateWeapon (int id, Weapon weapon);
    ResponseEntity<List<WeaponDTO>> saveWeapon (Weapon weapon);
    ResponseEntity<List<WeaponDTO>> deleteFromListOfWeapons (int id);
}
