package ua.project.java_advhomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.project.java_advhomework.dao.IWeaponDao;
import ua.project.java_advhomework.models.dto.WeaponDTO;
import ua.project.java_advhomework.models.entity.Weapon;
import ua.project.java_advhomework.services.IWeaponService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weapons")
@AllArgsConstructor
public class WeaponController {
    private IWeaponService weaponService;

    @GetMapping("")
    public ResponseEntity<List<WeaponDTO>> findAllWeapons() {
        return weaponService.findAllWeapons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeaponDTO> findWeaponById(@PathVariable int id) {
        return weaponService.findWeaponById(id);
    }

    @GetMapping("/findByCode_{code}")
    public ResponseEntity<List<WeaponDTO>> findByCode (@PathVariable String code) {
        return weaponService.findByCode(code);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WeaponDTO> updateWeapon (@PathVariable int id, @RequestBody Weapon weapon) {
        return weaponService.updateWeapon(id, weapon);
    }

    @PostMapping("")
    public ResponseEntity<List<WeaponDTO>> saveWeapon (@RequestBody Weapon weapon) {
        return weaponService.saveWeapon(weapon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<WeaponDTO>> deleteFromListOfWeapons (@PathVariable int id) {
        return weaponService.deleteFromListOfWeapons(id);
    }
}
