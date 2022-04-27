package ua.project.java_advhomework.services.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.project.java_advhomework.dao.IWeaponDao;
import ua.project.java_advhomework.models.dto.WeaponDTO;
import ua.project.java_advhomework.models.entity.Weapon;
import ua.project.java_advhomework.services.IWeaponService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class WeaponService implements IWeaponService {
    private IWeaponDao weaponDao;

    @Override
    public ResponseEntity<List<WeaponDTO>> findAllWeapons() {
        List<Weapon> weapons = weaponDao.findAll();
        List<WeaponDTO> weaponDTOS = weapons.stream().map(WeaponDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(weaponDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WeaponDTO> findWeaponById(int id) {
        Weapon weapon = weaponDao.findById(id).orElse(new Weapon());
        if (weapon.getId() == 0) {
            log.error("No content to show");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new WeaponDTO(weapon), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<WeaponDTO>> findByCode(String code) {
        List<Weapon> weapons = weaponDao.findByCode(code);
        List<WeaponDTO> weaponDTOS = weapons.stream().map(WeaponDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(weaponDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WeaponDTO> updateWeapon(int id, Weapon weapon) {
        if (weaponDao.findAll().stream().allMatch(weaponDao -> weaponDao.getId() != id)) {
            log.error("There is no such id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Weapon weapon1 = weaponDao.findById(id).get();
        if (weapon.getCode() != null) {
            weapon1.setCode(weapon.getCode());
            weaponDao.save(weapon1);
            return new ResponseEntity<>(new WeaponDTO(weapon1), HttpStatus.OK);
        } else {
            log.error("Weapon code is null!!!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<WeaponDTO>> saveWeapon(Weapon weapon) {
        if (weapon != null) {
            weaponDao.save(weapon);
            return new ResponseEntity<>(weaponDao.findAll().stream().map(WeaponDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("Weapon is null!!!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<WeaponDTO>> deleteFromListOfWeapons(int id) {
        if (id != 0) {
            weaponDao.deleteById(id);
            return new ResponseEntity<>(weaponDao
                    .findAll()
                    .stream()
                    .map(WeaponDTO::new)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("Error. Id is 0!!!");
            return new ResponseEntity<>(weaponDao
                    .findAll()
                    .stream()
                    .map(WeaponDTO::new)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
}
