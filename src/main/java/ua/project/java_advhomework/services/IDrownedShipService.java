package ua.project.java_advhomework.services;

import org.springframework.http.ResponseEntity;
import ua.project.java_advhomework.models.dto.*;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;

import java.util.List;

public interface IDrownedShipService {
    ResponseEntity<List<DrownedRussianShipDTO>> findAllDrownedWarships();
    ResponseEntity<DrownedRussianShipDTO> findDrownedWarshipById(int id);
    ResponseEntity<List<DrownedRussianShipDTO>> findByName(String name);
    ResponseEntity<List<DrownedRussianShipDTO>> findByYear(int year);
    ResponseEntity<List<DrownedRussianShipDTO>> findByTonnage(int tonnage);
    ResponseEntity<DrownedRussianShipDTO> updateDrownedWarship(int id, DrownedRussianShip drownedRussianShip);
    ResponseEntity<List<DrownedRussianShipDTO>> drownWarship(DrownedRussianShip drownedRussianShip);
    ResponseEntity<List<DrownedRussianShipDTO>> deleteFromListOfDrownedShips(int id);
    //////////////////
    ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findAllDrownedWarshipsWithCaptain();
    ResponseEntity<DrownedRussianShipWithCaptainDTO> findDrownedWarshipWithCaptainById(int id);
    ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByTonnage(int tonnage);
    ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByName(String name);
    ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByYear(int year);
    ResponseEntity<DrownedRussianShipWithCaptainDTO> updateDrownedWarshipWithCaptain(int id, DrownedRussianShip drownedRussianShip);
    ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> drownWarshipWithCaptain(DrownedRussianShip drownedRussianShip);
    ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> deleteFromListOfDrownedShipsWithCaptains(int id);
    //////////////////
    ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findAllDrownedWarshipsWithPreviousCaptain();
    ResponseEntity<DrownedRussianShipWithPreviousCaptainDTO> findDrownedWarshipWithPreviousCaptainById(int id);
    ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findDrownedWarshipWithPreviousCaptainByTonnage(int tonnage);
    ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findDrownedWarshipWithPreviousCaptainByName(String name);
    ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findDrownedWarshipWithPreviousCaptainByYear(int year);
    ResponseEntity<DrownedRussianShipWithPreviousCaptainDTO> updateDrownedWarshipWithPreviousCaptain(int id, DrownedRussianShip drownedRussianShip);
    ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> drownWarshipWithPreviousCaptain(DrownedRussianShip drownedRussianShip);
    ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> deleteFromListOfDrownedShipsWithPreviousCaptains(int id);
    //////////////////
    ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findAllDrownedWarshipsWithWeapon();
    ResponseEntity<DrownedRussianShipWithWeaponDTO> findDrownedWarshipWithWeapon(int id);
    ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findDrownedWarshipWithWeaponByTonnage(int tonnage);
    ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findDrownedWarshipWithWeaponByName(String name);
    ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findDrownedWarshipWithWeaponByYear(int year);
    ResponseEntity<DrownedRussianShipWithWeaponDTO> updateDrownedWarshipWithWeapon(int id, DrownedRussianShip drownedRussianShip);
    ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> drownWarshipWithWeapon(DrownedRussianShip drownedRussianShip);
    ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> deleteFromListOfDrownedShipsWithWeapon(int id);
    //////////////////
    ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findAllDrownedWarshipsWithAllComponents();
    ResponseEntity<DrownedRussianShipWithAllComponentsDTO> findDrownedWarshipWithAllComponents(int id);
    ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findDrownedWarshipWithAllComponentsByTonnage(int tonnage);
    ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findDrownedWarshipWithAllComponentsByName(String name);
    ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findDrownedWarshipWithAllComponentsByYear(int year);
    ResponseEntity<DrownedRussianShipWithAllComponentsDTO> updateDrownedWarshipWithAllComponents(int id, DrownedRussianShip drownedRussianShip);
    ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> drownWarshipWithAllComponents(DrownedRussianShip drownedRussianShip);
    ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> deleteFromListOfDrownedShipsWithAllComponents(int id);
















}
