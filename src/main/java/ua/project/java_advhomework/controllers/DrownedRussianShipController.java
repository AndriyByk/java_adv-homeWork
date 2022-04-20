package ua.project.java_advhomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.project.java_advhomework.dao.IRussianShipDao;
import ua.project.java_advhomework.models.dto.DrownedRussianShipDTO;
import ua.project.java_advhomework.models.dto.DrownedRussianShipWithCaptainDTO;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class DrownedRussianShipController {
    private IRussianShipDao shipDao;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to hell!";
    }

    @GetMapping("/warships_with_captains")
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findAllDrownedWarshipsWithCaptain() {
        List<DrownedRussianShip> allShips = shipDao.findAll();
        List<DrownedRussianShipWithCaptainDTO> allShipsDTO = allShips.stream().map(DrownedRussianShipWithCaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(allShipsDTO, HttpStatus.OK);
    }

    @GetMapping("/warships_with_captains/{id}")
    public ResponseEntity<DrownedRussianShipWithCaptainDTO> findDrownedWarshipWithCaptainById(@PathVariable int id) {
        DrownedRussianShip drownedRussianShip = shipDao.findById(id).orElse(new DrownedRussianShip());
        if (drownedRussianShip.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new DrownedRussianShipWithCaptainDTO(drownedRussianShip), HttpStatus.OK);
    }

    @GetMapping("/warships_with_captains/findByTonnage_{tonnage}")
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByTonnage(@PathVariable int tonnage) {
        List<DrownedRussianShip> shipsByTonnage = shipDao.findByTonnage(tonnage);
        List<DrownedRussianShipWithCaptainDTO> drownedRussianShipWithCaptainDTOS = shipsByTonnage.stream().map(DrownedRussianShipWithCaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithCaptainDTOS, HttpStatus.OK);
    }

    @GetMapping("/warships_with_captains/findByName_{name}")
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByName(@PathVariable String name) {
        List<DrownedRussianShip> shipsByName = shipDao.findByName(name);
        List<DrownedRussianShipWithCaptainDTO> drownedRussianShipWithCaptainDTOS = shipsByName.stream().map(DrownedRussianShipWithCaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithCaptainDTOS, HttpStatus.OK);
    }

    @GetMapping("/warships_with_captains/findByYear_{year}")
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByYear(@PathVariable int year) {
        List<DrownedRussianShip> shipsByYear = shipDao.findByYear(year);
        List<DrownedRussianShipWithCaptainDTO> drownedRussianShipWithCaptainDTOS = shipsByYear.stream().map(DrownedRussianShipWithCaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithCaptainDTOS, HttpStatus.OK);
    }

    @PatchMapping("/warships_with_captains/{id}")
    public ResponseEntity<DrownedRussianShipWithCaptainDTO> updateDrownedWarshipWithCaptain(@PathVariable int id, @RequestBody DrownedRussianShip drownedRussianShip) {
        if (shipDao.findAll().stream().allMatch(shipDao -> shipDao.getId() != id))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<>(new DrownedRussianShipWithCaptainDTO(ship), HttpStatus.OK);
    }

    @PostMapping("/warships_with_captains")
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> drownWarshipWithCaptain(@RequestBody DrownedRussianShip drownedRussianShip) {
        if (drownedRussianShip != null) {
            shipDao.save(drownedRussianShip);
            return new ResponseEntity<>(shipDao.findAll().stream().map(DrownedRussianShipWithCaptainDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/warships_with_captains/{id}")
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> deleteFromListOfDrownedShipsWithCaptains(@PathVariable int id) {
        if (shipDao.findAll().stream().anyMatch(shipDao -> shipDao.getId() == id)) {
            shipDao.deleteById(id);
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipWithCaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipWithCaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/warships")
    public ResponseEntity<List<DrownedRussianShipDTO>> findAllDrownedWarships() {
        return new ResponseEntity<>(shipDao
                .findAll()
                .stream()
                .map(DrownedRussianShipDTO::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/warships/{id}")
    public ResponseEntity<DrownedRussianShipDTO> findDrownedWarshipById(@PathVariable int id) {
        DrownedRussianShip drownedRussianShip = shipDao.findById(id).orElse(new DrownedRussianShip());
        if (drownedRussianShip.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new DrownedRussianShipDTO(drownedRussianShip), HttpStatus.OK);
    }

    @GetMapping("/warships/findByName_{name}")
    public ResponseEntity<List<DrownedRussianShipDTO>> findByName(@PathVariable String name) {
        List<DrownedRussianShip> shipsByName = shipDao.findByName(name);
        List<DrownedRussianShipDTO> drownedRussianShipDTOS = shipsByName.stream().map(DrownedRussianShipDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipDTOS, HttpStatus.OK);
    }

    @GetMapping("/warships/findByYear_{year}")
    public ResponseEntity<List<DrownedRussianShipDTO>> findByYear(@PathVariable int year) {
        List<DrownedRussianShip> shipsByYear = shipDao.findByYear(year);
        List<DrownedRussianShipDTO> drownedRussianShipDTOS = shipsByYear.stream().map(DrownedRussianShipDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipDTOS, HttpStatus.OK);
    }

    @GetMapping("/warships/findByTonnage_{tonnage}")
    public ResponseEntity<List<DrownedRussianShipDTO>> findByTonnage(@PathVariable int tonnage) {
        List<DrownedRussianShip> shipsByTonnage = shipDao.findByTonnage(tonnage);
        List<DrownedRussianShipDTO> drownedRussianShipDTOS = shipsByTonnage.stream().map(DrownedRussianShipDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipDTOS, HttpStatus.OK);
    }

    @PatchMapping("/warships/{id}")
    public ResponseEntity<DrownedRussianShipDTO> updateDrownedWarship(@PathVariable int id, @RequestBody DrownedRussianShip drownedRussianShip) {
        if (shipDao.findAll().stream().allMatch(shipDao -> shipDao.getId() != id))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<>(new DrownedRussianShipDTO(ship), HttpStatus.OK);
    }

    @PostMapping("/warships")
    public ResponseEntity<List<DrownedRussianShipDTO>> drownWarship(@RequestBody DrownedRussianShip drownedRussianShip) {
        if (drownedRussianShip != null) {
            shipDao.save(drownedRussianShip);
            return new ResponseEntity<>(shipDao.findAll().stream().map(DrownedRussianShipDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/warships/{id}")
    public ResponseEntity<List<DrownedRussianShipDTO>> deleteFromListOfDrownedShips(@PathVariable int id) {
        if (shipDao.findAll().stream().anyMatch(shipDao -> shipDao.getId() == id)) {
            shipDao.deleteById(id);
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipDTO::new)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipDTO::new)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
}
