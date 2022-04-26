package ua.project.java_advhomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.project.java_advhomework.dao.IWeaponDao;
import ua.project.java_advhomework.models.dto.WeaponDTO;
import ua.project.java_advhomework.models.entity.Weapon;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weapons")
@AllArgsConstructor
public class WeaponController {
    private IWeaponDao weaponDao;

    @GetMapping("")
    public ResponseEntity<List<WeaponDTO>> findAllWeapons() {
        List<Weapon> weapons = weaponDao.findAll();
        List<WeaponDTO> weaponDTOS = weapons.stream().map(WeaponDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(weaponDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeaponDTO> findWeaponById(@PathVariable int id) {
        Weapon weapon = weaponDao.findById(id).orElse(new Weapon());
        if (weapon.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new WeaponDTO(weapon), HttpStatus.OK);
    }

    @GetMapping("/findByCode_{code}")
    public ResponseEntity<List<WeaponDTO>> findByCode (@PathVariable String code) {
        List<Weapon> weapons = weaponDao.findByCode(code);
        List<WeaponDTO> weaponDTOS = weapons.stream().map(WeaponDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(weaponDTOS, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WeaponDTO> updateWeapon (@PathVariable int id, @RequestBody Weapon weapon) {
        Weapon weapon1 = weaponDao.findById(id).get();
        if (weapon.getCode() != null) {
            weapon1.setCode(weapon.getCode());
            weaponDao.save(weapon1);
            return new ResponseEntity<>(new WeaponDTO(weapon1), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    public ResponseEntity<List<WeaponDTO>> saveWeapon (@RequestBody Weapon weapon) {
        if (weapon != null) {
            weaponDao.save(weapon);
            return new ResponseEntity<>(weaponDao.findAll().stream().map(WeaponDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<WeaponDTO>> deleteFromListOfWeapons (@PathVariable int id) {
        if (id != 0) {
            weaponDao.deleteById(id);
            return new ResponseEntity<>(weaponDao
                    .findAll()
                    .stream()
                    .map(WeaponDTO::new)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(weaponDao
                    .findAll()
                    .stream()
                    .map(WeaponDTO::new)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
}
