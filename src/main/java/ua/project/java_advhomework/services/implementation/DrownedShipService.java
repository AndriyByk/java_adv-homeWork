package ua.project.java_advhomework.services.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.project.java_advhomework.dao.IRussianShipDao;
import ua.project.java_advhomework.models.dto.*;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;
import ua.project.java_advhomework.services.IDrownedShipService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DrownedShipService implements IDrownedShipService {
    private IRussianShipDao shipDao;

    @Override
    public ResponseEntity<List<DrownedRussianShipDTO>> findAllDrownedWarships() {
        return new ResponseEntity<>(shipDao
                .findAll()
                .stream()
                .map(DrownedRussianShipDTO::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipDTO> findDrownedWarshipById(int id) {
        DrownedRussianShip drownedRussianShip = shipDao.findById(id).orElse(new DrownedRussianShip());
        if (drownedRussianShip.getId() == 0) {
            log.error("No content to show");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new DrownedRussianShipDTO(drownedRussianShip), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipDTO>> findByName(String name) {
        List<DrownedRussianShip> shipsByName = shipDao.findByName(name);
        List<DrownedRussianShipDTO> drownedRussianShipDTOS = shipsByName.stream().map(DrownedRussianShipDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipDTO>> findByYear(int year) {
        List<DrownedRussianShip> shipsByYear = shipDao.findByYear(year);
        List<DrownedRussianShipDTO> drownedRussianShipDTOS = shipsByYear.stream().map(DrownedRussianShipDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipDTO>> findByTonnage(int tonnage) {
        List<DrownedRussianShip> shipsByTonnage = shipDao.findByTonnage(tonnage);
        List<DrownedRussianShipDTO> drownedRussianShipDTOS = shipsByTonnage.stream().map(DrownedRussianShipDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipDTO> updateDrownedWarship(int id, DrownedRussianShip drownedRussianShip) {
        if (shipDao.findAll().stream().allMatch(shipDao -> shipDao.getId() != id)) {
            log.error("There is no such id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    @Override
    public ResponseEntity<List<DrownedRussianShipDTO>> drownWarship(DrownedRussianShip drownedRussianShip) {
        if (drownedRussianShip != null) {
            shipDao.save(drownedRussianShip);
            return new ResponseEntity<>(shipDao.findAll().stream().map(DrownedRussianShipDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("DrownedRussianShip is null!!!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipDTO>> deleteFromListOfDrownedShips(int id) {
        if (shipDao.findAll().stream().anyMatch(shipDao -> shipDao.getId() == id)) {
            shipDao.deleteById(id);
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipDTO::new)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("There is no such id!!!");
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipDTO::new)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findAllDrownedWarshipsWithCaptain() {
        List<DrownedRussianShip> allShips = shipDao.findAll();
        List<DrownedRussianShipWithCaptainDTO> allShipsDTO = allShips.stream().map(DrownedRussianShipWithCaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(allShipsDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipWithCaptainDTO> findDrownedWarshipWithCaptainById(int id) {
        DrownedRussianShip drownedRussianShip = shipDao.findById(id).orElse(new DrownedRussianShip());
        if (drownedRussianShip.getId() == 0) {
            log.error("No content to show");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new DrownedRussianShipWithCaptainDTO(drownedRussianShip), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByTonnage(int tonnage) {
        List<DrownedRussianShip> shipsByTonnage = shipDao.findByTonnage(tonnage);
        List<DrownedRussianShipWithCaptainDTO> drownedRussianShipWithCaptainDTOS = shipsByTonnage.stream().map(DrownedRussianShipWithCaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByName(String name) {
        List<DrownedRussianShip> shipsByName = shipDao.findByName(name);
        List<DrownedRussianShipWithCaptainDTO> drownedRussianShipWithCaptainDTOS = shipsByName.stream().map(DrownedRussianShipWithCaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByYear(int year) {
        List<DrownedRussianShip> shipsByYear = shipDao.findByYear(year);
        List<DrownedRussianShipWithCaptainDTO> drownedRussianShipWithCaptainDTOS = shipsByYear.stream().map(DrownedRussianShipWithCaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipWithCaptainDTO> updateDrownedWarshipWithCaptain(int id, DrownedRussianShip drownedRussianShip) {
        if (shipDao.findAll().stream().allMatch(shipDao -> shipDao.getId() != id)) {
            log.error("There is no such id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    @Override
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> drownWarshipWithCaptain(DrownedRussianShip drownedRussianShip) {
        if (drownedRussianShip != null) {
            shipDao.save(drownedRussianShip);
            return new ResponseEntity<>(shipDao.findAll().stream().map(DrownedRussianShipWithCaptainDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("DrownedRussianShip is null!!!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> deleteFromListOfDrownedShipsWithCaptains(int id) {
        if (shipDao.findAll().stream().anyMatch(shipDao -> shipDao.getId() == id)) {
            shipDao.deleteById(id);
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipWithCaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("There is no such id!!!");
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipWithCaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findAllDrownedWarshipsWithPreviousCaptain() {
        List<DrownedRussianShip> allShips = shipDao.findAll();
        List<DrownedRussianShipWithPreviousCaptainDTO> allDrownedRussianShipWithPreviousCaptainDTOS = allShips
                .stream()
                .map(DrownedRussianShipWithPreviousCaptainDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allDrownedRussianShipWithPreviousCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipWithPreviousCaptainDTO> findDrownedWarshipWithPreviousCaptainById(int id) {
        DrownedRussianShip drownedRussianShip = shipDao.findById(id).orElse(new DrownedRussianShip());
        if (drownedRussianShip.getId() == 0) {
            log.error("No content to show");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new DrownedRussianShipWithPreviousCaptainDTO(drownedRussianShip), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findDrownedWarshipWithPreviousCaptainByTonnage(int tonnage) {
        List<DrownedRussianShip> shipsByTonnage = shipDao.findByTonnage(tonnage);
        List<DrownedRussianShipWithPreviousCaptainDTO> drownedRussianShipWithPreviousCaptainDTOS = shipsByTonnage.stream().map(DrownedRussianShipWithPreviousCaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithPreviousCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findDrownedWarshipWithPreviousCaptainByName(String name) {
        List<DrownedRussianShip> shipsByName = shipDao.findByName(name);
        List<DrownedRussianShipWithPreviousCaptainDTO> drownedRussianShipWithPreviousCaptainDTOS = shipsByName.stream().map(DrownedRussianShipWithPreviousCaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithPreviousCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findDrownedWarshipWithPreviousCaptainByYear(int year) {
        List<DrownedRussianShip> shipsByYear = shipDao.findByYear(year);
        List<DrownedRussianShipWithPreviousCaptainDTO> drownedRussianShipWithPreviousCaptainDTOS = shipsByYear.stream().map(DrownedRussianShipWithPreviousCaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithPreviousCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipWithPreviousCaptainDTO> updateDrownedWarshipWithPreviousCaptain(int id, DrownedRussianShip drownedRussianShip) {
        if (shipDao.findAll().stream().allMatch(shipDao -> shipDao.getId() != id)) {
            log.error("There is no such id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
        return new ResponseEntity<>(new DrownedRussianShipWithPreviousCaptainDTO(ship), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> drownWarshipWithPreviousCaptain(DrownedRussianShip drownedRussianShip) {
        if (drownedRussianShip != null) {
            shipDao.save(drownedRussianShip);
            return new ResponseEntity<>(shipDao.findAll().stream().map(DrownedRussianShipWithPreviousCaptainDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("DrownedRussianShip is null!!!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> deleteFromListOfDrownedShipsWithPreviousCaptains(int id) {
        if (shipDao.findAll().stream().anyMatch(shipDao -> shipDao.getId() == id)) {
            shipDao.deleteById(id);
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipWithPreviousCaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("There is no such id!!!");
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipWithPreviousCaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findAllDrownedWarshipsWithWeapon() {
        List<DrownedRussianShip> allShips = shipDao.findAll();
        List<DrownedRussianShipWithWeaponDTO> allDrownedRussianShipWithWeaponDTOS = allShips
                .stream()
                .map(DrownedRussianShipWithWeaponDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allDrownedRussianShipWithWeaponDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipWithWeaponDTO> findDrownedWarshipWithWeapon(int id) {
        DrownedRussianShip drownedRussianShip = shipDao.findById(id).orElse(new DrownedRussianShip());
        if (drownedRussianShip.getId() == 0) {
            log.error("No content to show");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new DrownedRussianShipWithWeaponDTO(drownedRussianShip), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findDrownedWarshipWithWeaponByTonnage(int tonnage) {
        List<DrownedRussianShip> shipsByTonnage = shipDao.findByTonnage(tonnage);
        List<DrownedRussianShipWithWeaponDTO> drownedRussianShipWithWeaponDTOS = shipsByTonnage.stream().map(DrownedRussianShipWithWeaponDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithWeaponDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findDrownedWarshipWithWeaponByName(String name) {
        List<DrownedRussianShip> shipsByName = shipDao.findByName(name);
        List<DrownedRussianShipWithWeaponDTO> drownedRussianShipWithWeaponDTOS = shipsByName.stream().map(DrownedRussianShipWithWeaponDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithWeaponDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findDrownedWarshipWithWeaponByYear(int year) {
        List<DrownedRussianShip> shipsByYear = shipDao.findByYear(year);
        List<DrownedRussianShipWithWeaponDTO> drownedRussianShipWithWeaponDTOS = shipsByYear.stream().map(DrownedRussianShipWithWeaponDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithWeaponDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipWithWeaponDTO> updateDrownedWarshipWithWeapon(int id, DrownedRussianShip drownedRussianShip) {
        if (shipDao.findAll().stream().allMatch(shipDao -> shipDao.getId() != id)) {
            log.error("There is no such id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
        return new ResponseEntity<>(new DrownedRussianShipWithWeaponDTO(ship), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> drownWarshipWithWeapon(DrownedRussianShip drownedRussianShip) {
        if (drownedRussianShip != null) {
            shipDao.save(drownedRussianShip);
            return new ResponseEntity<>(shipDao.findAll().stream().map(DrownedRussianShipWithWeaponDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("DrownedRussianShip is null!!!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> deleteFromListOfDrownedShipsWithWeapon(int id) {
        if (shipDao.findAll().stream().anyMatch(shipDao -> shipDao.getId() == id)) {
            shipDao.deleteById(id);
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipWithWeaponDTO::new)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("There is no such id!!!");
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipWithWeaponDTO::new)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findAllDrownedWarshipsWithAllComponents() {
        List<DrownedRussianShip> allShips = shipDao.findAll();
        List<DrownedRussianShipWithAllComponentsDTO> allDrownedRussianShipWithAllComponents = allShips
                .stream()
                .map(DrownedRussianShipWithAllComponentsDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allDrownedRussianShipWithAllComponents, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipWithAllComponentsDTO> findDrownedWarshipWithAllComponents(int id) {
        DrownedRussianShip drownedRussianShip = shipDao.findById(id).orElse(new DrownedRussianShip());
        if (drownedRussianShip.getId() == 0) {
            log.error("No content to show");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new DrownedRussianShipWithAllComponentsDTO(drownedRussianShip), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findDrownedWarshipWithAllComponentsByTonnage(int tonnage) {
        List<DrownedRussianShip> shipsByTonnage = shipDao.findByTonnage(tonnage);
        List<DrownedRussianShipWithAllComponentsDTO> drownedRussianShipWithAllComponentsDTOS = shipsByTonnage.stream().map(DrownedRussianShipWithAllComponentsDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithAllComponentsDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findDrownedWarshipWithAllComponentsByName(String name) {
        List<DrownedRussianShip> shipsByName = shipDao.findByName(name);
        List<DrownedRussianShipWithAllComponentsDTO> drownedRussianShipWithAllComponentsDTOS = shipsByName.stream().map(DrownedRussianShipWithAllComponentsDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithAllComponentsDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findDrownedWarshipWithAllComponentsByYear(int year) {
        List<DrownedRussianShip> shipsByYear = shipDao.findByYear(year);
        List<DrownedRussianShipWithAllComponentsDTO> drownedRussianShipWithAllComponentsDTOS = shipsByYear.stream().map(DrownedRussianShipWithAllComponentsDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(drownedRussianShipWithAllComponentsDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipWithAllComponentsDTO> updateDrownedWarshipWithAllComponents(int id, DrownedRussianShip drownedRussianShip) {
        if (shipDao.findAll().stream().allMatch(shipDao -> shipDao.getId() != id)) {
            log.error("There is no such id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
        return new ResponseEntity<>(new DrownedRussianShipWithAllComponentsDTO(ship), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> drownWarshipWithAllComponents(DrownedRussianShip drownedRussianShip) {
        if (drownedRussianShip != null) {
            shipDao.save(drownedRussianShip);
            return new ResponseEntity<>(shipDao.findAll().stream().map(DrownedRussianShipWithAllComponentsDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("DrownedRussianShip is null!!!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> deleteFromListOfDrownedShipsWithAllComponents(int id) {
        if (shipDao.findAll().stream().anyMatch(shipDao -> shipDao.getId() == id)) {
            shipDao.deleteById(id);
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipWithAllComponentsDTO::new)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("There is no such id!!!");
            return new ResponseEntity<>(shipDao
                    .findAll()
                    .stream()
                    .map(DrownedRussianShipWithAllComponentsDTO::new)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
}
