package ua.project.java_advhomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.project.java_advhomework.dao.IRussianShipDao;
import ua.project.java_advhomework.models.DrownedRussianShip;

import java.util.List;

@RestController
@AllArgsConstructor
public class RussianShipController {
    private IRussianShipDao shipDao;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to hell!";
    }

    @GetMapping("/warships")
    public ResponseEntity<List<DrownedRussianShip>> findAllDrownedWarships() {
        return new ResponseEntity<>(shipDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/warships/{id}")
    public ResponseEntity<DrownedRussianShip> findDrownedWarshipById(@PathVariable int id) {
        DrownedRussianShip drownedRussianShip = shipDao.findById(id).orElse(new DrownedRussianShip());
        if (drownedRussianShip.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(drownedRussianShip, HttpStatus.OK);
    }

    @PatchMapping("/warships/{id}")
    public ResponseEntity<DrownedRussianShip> updateDrownedWarship(@PathVariable int id, @RequestBody DrownedRussianShip drownedRussianShip) {
        DrownedRussianShip ship = shipDao.findById(id).get();
        if (drownedRussianShip.getName() != null)
            ship.setName(drownedRussianShip.getName());
        if (drownedRussianShip.getYear() != 0)
            ship.setYear(drownedRussianShip.getYear());
        if (drownedRussianShip.getTonnage() != 0)
            ship.setTonnage(drownedRussianShip.getTonnage());
        if (drownedRussianShip.getName() == null &&
                drownedRussianShip.getYear() == 0 &&
                drownedRussianShip.getTonnage() == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        shipDao.save(ship);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @PostMapping("/warships")
    public ResponseEntity<List<DrownedRussianShip>> drownWarship(@RequestBody DrownedRussianShip drownedRussianShip) {
        if (drownedRussianShip != null) {
            shipDao.save(drownedRussianShip);
            return new ResponseEntity<>(shipDao.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(shipDao.findAll(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/warships/{id}")
    public ResponseEntity<List<DrownedRussianShip>> deleteFromListOfDrownedShips (@PathVariable int id) {
        if (id != 0) {
            shipDao.deleteById(id);
            return new ResponseEntity<>(shipDao.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(shipDao.findAll(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/warships/findByTonnage_{tonnage}")
    public List<DrownedRussianShip> findByTonnage (@PathVariable int tonnage) {
        return shipDao.findByTonnage(tonnage);
    }

    @GetMapping("/warships/findByName_{name}")
    public List<DrownedRussianShip> findByName (@PathVariable String name) {
        return shipDao.findByName(name);
    }

    @GetMapping("/warships/findByYear_{year}")
    public List<DrownedRussianShip> findByYear (@PathVariable int year) {
        return shipDao.findByYear(year);
    }
}
