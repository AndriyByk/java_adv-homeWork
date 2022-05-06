package ua.project.java_advhomework.services.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.project.java_advhomework.dao.IRussianShipDao;
import ua.project.java_advhomework.models.dto.*;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;
import ua.project.java_advhomework.services.DossierByMailService;
import ua.project.java_advhomework.services.IDrownedShipService;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DrownedShipService implements IDrownedShipService {
    private IRussianShipDao shipDao;
    private DossierByMailService dossierSender;

    @Override
    public ResponseEntity<List<DrownedRussianShip>> drownWarshipWithMailAndPicture(MultipartFile picture, String name, String email) throws IOException, MessagingException {
        DrownedRussianShip drownedShip = new DrownedRussianShip(picture.getOriginalFilename(), name, email);
        shipDao.save(drownedShip);

        DrownedRussianShipForMailingDTO drownedShipWithMail = new DrownedRussianShipForMailingDTO(drownedShip);
        dossierSender.send(drownedShipWithMail, picture);

        String path = System.getProperty("user.home") + File.separator + "Pictures" + File.separator;
        picture.transferTo(new File(path + picture.getOriginalFilename()));

        return new ResponseEntity<>(shipDao.findAll(), HttpStatus.OK);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public ResponseEntity<List<DrownedRussianShip>> drownWarshipWithPicture (
            MultipartFile picture,
            String name,
            int year,
            int tonnage
    ) throws IOException {
        shipDao.save(new DrownedRussianShip(picture.getOriginalFilename(), name, year, tonnage));

        String path = System.getProperty("user.home") + File.separator + "Pictures" + File.separator;
        picture.transferTo(new File(path + picture.getOriginalFilename()));
        return new ResponseEntity<>(shipDao.findAll(), HttpStatus.OK);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public ResponseEntity<List<DrownedRussianShipDTO>> findAllDrownedWarships() {
        log.info("All drowned warships were found.");
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
        log.info("Drowned warship was found.");
        return new ResponseEntity<>(new DrownedRussianShipDTO(drownedRussianShip), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipDTO>> findByName(String name) {
        List<DrownedRussianShip> shipsByName = shipDao.findByName(name);
        List<DrownedRussianShipDTO> drownedRussianShipDTOS = shipsByName.stream().map(DrownedRussianShipDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate name were found.");
        return new ResponseEntity<>(drownedRussianShipDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipDTO>> findByYear(int year) {
        List<DrownedRussianShip> shipsByYear = shipDao.findByYear(year);
        List<DrownedRussianShipDTO> drownedRussianShipDTOS = shipsByYear.stream().map(DrownedRussianShipDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate year was found.");
        return new ResponseEntity<>(drownedRussianShipDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipDTO>> findByTonnage(int tonnage) {
        List<DrownedRussianShip> shipsByTonnage = shipDao.findByTonnage(tonnage);
        List<DrownedRussianShipDTO> drownedRussianShipDTOS = shipsByTonnage.stream().map(DrownedRussianShipDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate tonnage was found.");
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
        log.info("Drowned warship was successfully updated.");
        return new ResponseEntity<>(new DrownedRussianShipDTO(ship), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipDTO>> drownWarship(DrownedRussianShip drownedRussianShip) {
        if (drownedRussianShip != null) {
            shipDao.save(drownedRussianShip);
            log.info("Warship was successfully drowned!!!!!!");
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
            log.info("Warship was deleted from list");
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
        log.info("All drowned warships with captain were found.");
        return new ResponseEntity<>(allShipsDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipWithCaptainDTO> findDrownedWarshipWithCaptainById(int id) {
        DrownedRussianShip drownedRussianShip = shipDao.findById(id).orElse(new DrownedRussianShip());
        if (drownedRussianShip.getId() == 0) {
            log.error("No content to show");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("Drowned warship with captain was found.");
        return new ResponseEntity<>(new DrownedRussianShipWithCaptainDTO(drownedRussianShip), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByTonnage(int tonnage) {
        List<DrownedRussianShip> shipsByTonnage = shipDao.findByTonnage(tonnage);
        List<DrownedRussianShipWithCaptainDTO> drownedRussianShipWithCaptainDTOS = shipsByTonnage.stream().map(DrownedRussianShipWithCaptainDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate tonnage were found.");
        return new ResponseEntity<>(drownedRussianShipWithCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByName(String name) {
        List<DrownedRussianShip> shipsByName = shipDao.findByName(name);
        List<DrownedRussianShipWithCaptainDTO> drownedRussianShipWithCaptainDTOS = shipsByName.stream().map(DrownedRussianShipWithCaptainDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate name were found.");
        return new ResponseEntity<>(drownedRussianShipWithCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByYear(int year) {
        List<DrownedRussianShip> shipsByYear = shipDao.findByYear(year);
        List<DrownedRussianShipWithCaptainDTO> drownedRussianShipWithCaptainDTOS = shipsByYear.stream().map(DrownedRussianShipWithCaptainDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate year were found.");
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
        log.info("Drowned warship was successfully updated.");
        return new ResponseEntity<>(new DrownedRussianShipWithCaptainDTO(ship), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> drownWarshipWithCaptain(DrownedRussianShip drownedRussianShip) {
        if (drownedRussianShip != null) {
            shipDao.save(drownedRussianShip);
            log.info("Warship was successfully drowned!!!!!!");
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
            log.info("Warship was deleted from list");
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
        log.info("All drowned warships with previous captains were found.");
        return new ResponseEntity<>(allDrownedRussianShipWithPreviousCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipWithPreviousCaptainDTO> findDrownedWarshipWithPreviousCaptainById(int id) {
        DrownedRussianShip drownedRussianShip = shipDao.findById(id).orElse(new DrownedRussianShip());
        if (drownedRussianShip.getId() == 0) {
            log.error("No content to show");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("Drowned warship with previous captains was found.");
        return new ResponseEntity<>(new DrownedRussianShipWithPreviousCaptainDTO(drownedRussianShip), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findDrownedWarshipWithPreviousCaptainByTonnage(int tonnage) {
        List<DrownedRussianShip> shipsByTonnage = shipDao.findByTonnage(tonnage);
        List<DrownedRussianShipWithPreviousCaptainDTO> drownedRussianShipWithPreviousCaptainDTOS = shipsByTonnage.stream().map(DrownedRussianShipWithPreviousCaptainDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate tonnage were found.");
        return new ResponseEntity<>(drownedRussianShipWithPreviousCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findDrownedWarshipWithPreviousCaptainByName(String name) {
        List<DrownedRussianShip> shipsByName = shipDao.findByName(name);
        List<DrownedRussianShipWithPreviousCaptainDTO> drownedRussianShipWithPreviousCaptainDTOS = shipsByName.stream().map(DrownedRussianShipWithPreviousCaptainDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate name were found.");
        return new ResponseEntity<>(drownedRussianShipWithPreviousCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findDrownedWarshipWithPreviousCaptainByYear(int year) {
        List<DrownedRussianShip> shipsByYear = shipDao.findByYear(year);
        List<DrownedRussianShipWithPreviousCaptainDTO> drownedRussianShipWithPreviousCaptainDTOS = shipsByYear.stream().map(DrownedRussianShipWithPreviousCaptainDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate year were found.");
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
        log.info("Drowned warship was successfully updated.");
        return new ResponseEntity<>(new DrownedRussianShipWithPreviousCaptainDTO(ship), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> drownWarshipWithPreviousCaptain(DrownedRussianShip drownedRussianShip) {
        if (drownedRussianShip != null) {
            shipDao.save(drownedRussianShip);
            log.info("Warship was successfully drowned!!!!!!");
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
            log.info("Warship was deleted from list");
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
        log.info("All drowned warships with weapon were found.");
        return new ResponseEntity<>(allDrownedRussianShipWithWeaponDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipWithWeaponDTO> findDrownedWarshipWithWeapon(int id) {
        DrownedRussianShip drownedRussianShip = shipDao.findById(id).orElse(new DrownedRussianShip());
        if (drownedRussianShip.getId() == 0) {
            log.error("No content to show");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("Drowned warship with weapon was found.");
        return new ResponseEntity<>(new DrownedRussianShipWithWeaponDTO(drownedRussianShip), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findDrownedWarshipWithWeaponByTonnage(int tonnage) {
        List<DrownedRussianShip> shipsByTonnage = shipDao.findByTonnage(tonnage);
        List<DrownedRussianShipWithWeaponDTO> drownedRussianShipWithWeaponDTOS = shipsByTonnage.stream().map(DrownedRussianShipWithWeaponDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate tonnage were found.");
        return new ResponseEntity<>(drownedRussianShipWithWeaponDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findDrownedWarshipWithWeaponByName(String name) {
        List<DrownedRussianShip> shipsByName = shipDao.findByName(name);
        List<DrownedRussianShipWithWeaponDTO> drownedRussianShipWithWeaponDTOS = shipsByName.stream().map(DrownedRussianShipWithWeaponDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate name were found.");
        return new ResponseEntity<>(drownedRussianShipWithWeaponDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findDrownedWarshipWithWeaponByYear(int year) {
        List<DrownedRussianShip> shipsByYear = shipDao.findByYear(year);
        List<DrownedRussianShipWithWeaponDTO> drownedRussianShipWithWeaponDTOS = shipsByYear.stream().map(DrownedRussianShipWithWeaponDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate year were found.");
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
        log.info("Drowned warship was successfully updated.");
        return new ResponseEntity<>(new DrownedRussianShipWithWeaponDTO(ship), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> drownWarshipWithWeapon(DrownedRussianShip drownedRussianShip) {
        if (drownedRussianShip != null) {
            shipDao.save(drownedRussianShip);
            log.info("Warship was successfully drowned!!!!!!");
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
            log.info("Warship was deleted from list");
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
        log.info("All drowned warships with captain, all previous captains and weapon were found.");
        return new ResponseEntity<>(allDrownedRussianShipWithAllComponents, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DrownedRussianShipWithAllComponentsDTO> findDrownedWarshipWithAllComponents(int id) {
        DrownedRussianShip drownedRussianShip = shipDao.findById(id).orElse(new DrownedRussianShip());
        if (drownedRussianShip.getId() == 0) {
            log.error("No content to show");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("Drowned warship with captain, all previous captains and weapon was found.");
        return new ResponseEntity<>(new DrownedRussianShipWithAllComponentsDTO(drownedRussianShip), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findDrownedWarshipWithAllComponentsByTonnage(int tonnage) {
        List<DrownedRussianShip> shipsByTonnage = shipDao.findByTonnage(tonnage);
        List<DrownedRussianShipWithAllComponentsDTO> drownedRussianShipWithAllComponentsDTOS = shipsByTonnage.stream().map(DrownedRussianShipWithAllComponentsDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate tonnage were found.");
        return new ResponseEntity<>(drownedRussianShipWithAllComponentsDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findDrownedWarshipWithAllComponentsByName(String name) {
        List<DrownedRussianShip> shipsByName = shipDao.findByName(name);
        List<DrownedRussianShipWithAllComponentsDTO> drownedRussianShipWithAllComponentsDTOS = shipsByName.stream().map(DrownedRussianShipWithAllComponentsDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate name were found.");
        return new ResponseEntity<>(drownedRussianShipWithAllComponentsDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findDrownedWarshipWithAllComponentsByYear(int year) {
        List<DrownedRussianShip> shipsByYear = shipDao.findByYear(year);
        List<DrownedRussianShipWithAllComponentsDTO> drownedRussianShipWithAllComponentsDTOS = shipsByYear.stream().map(DrownedRussianShipWithAllComponentsDTO::new).collect(Collectors.toList());
        log.info("All drowned warships with appropriate year were found.");
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
        log.info("Drowned warship was successfully updated.");
        return new ResponseEntity<>(new DrownedRussianShipWithAllComponentsDTO(ship), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> drownWarshipWithAllComponents(DrownedRussianShip drownedRussianShip) {
        if (drownedRussianShip != null) {
            shipDao.save(drownedRussianShip);
            log.info("Warship was successfully drowned!!!!!!");
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
            log.info("Warship was deleted from list");
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
