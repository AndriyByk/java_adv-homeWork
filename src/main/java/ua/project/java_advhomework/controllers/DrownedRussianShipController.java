package ua.project.java_advhomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.project.java_advhomework.dao.IRussianShipDao;
import ua.project.java_advhomework.filters.DrownedRussianShipFilter;
import ua.project.java_advhomework.models.dto.*;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;
import ua.project.java_advhomework.services.IDrownedShipService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class DrownedRussianShipController {
    private IDrownedShipService shipService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to hell!";
    }

    @GetMapping("/warships_with_all_components")
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findAllDrownedWarshipsWithAllComponents() {
        return shipService.findAllDrownedWarshipsWithAllComponents();
    }

    @GetMapping("/warships_with_all_components/{id}")
    public ResponseEntity<DrownedRussianShipWithAllComponentsDTO> findDrownedWarshipWithAllComponents(@PathVariable int id) {
        return shipService.findDrownedWarshipWithAllComponents(id);
    }

    @GetMapping("/warships_with_all_components/findByTonnage_{tonnage}")
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findDrownedWarshipWithAllComponentsByTonnage(@PathVariable int tonnage) {
        return shipService.findDrownedWarshipWithAllComponentsByTonnage(tonnage);
    }

    @GetMapping("/warships_with_all_components/findByName_{name}")
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findDrownedWarshipWithAllComponentsByName(@PathVariable String name) {
        return shipService.findDrownedWarshipWithAllComponentsByName(name);
    }

    @GetMapping("/warships_with_all_components/findByYear_{year}")
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> findDrownedWarshipWithAllComponentsByYear(@PathVariable int year) {
        return shipService.findDrownedWarshipWithAllComponentsByYear(year);
    }

    @PatchMapping("/warships_with_all_components/{id}")
    public ResponseEntity<DrownedRussianShipWithAllComponentsDTO> updateDrownedWarshipWithAllComponents(@PathVariable int id, @RequestBody DrownedRussianShip drownedRussianShip) {
        return shipService.updateDrownedWarshipWithAllComponents(id, drownedRussianShip);
    }

    @PostMapping("/warships_with_all_components")
    public void drownWarshipWithAllComponents() {
    }

    @DeleteMapping("/warships_with_all_components/{id}")
    public ResponseEntity<List<DrownedRussianShipWithAllComponentsDTO>> deleteFromListOfDrownedShipsWithAllComponents(@PathVariable int id) {
        return shipService.deleteFromListOfDrownedShipsWithAllComponents(id);
    }

    ///////////////////////////////

    @GetMapping("/warships_with_weapon")
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findAllDrownedWarshipsWithWeapon() {
        return shipService.findAllDrownedWarshipsWithWeapon();
    }

    @GetMapping("/warships_with_weapon/{id}")
    public ResponseEntity<DrownedRussianShipWithWeaponDTO> findDrownedWarshipWithWeapon(@PathVariable int id) {
        return shipService.findDrownedWarshipWithWeapon(id);
    }

    @GetMapping("/warships_with_weapon/findByTonnage_{tonnage}")
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findDrownedWarshipWithWeaponByTonnage(@PathVariable int tonnage) {
        return shipService.findDrownedWarshipWithWeaponByTonnage(tonnage);
    }

    @GetMapping("/warships_with_weapon/findByName_{name}")
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findDrownedWarshipWithWeaponByName(@PathVariable String name) {
        return shipService.findDrownedWarshipWithWeaponByName(name);
    }

    @GetMapping("/warships_with_weapon/findByYear_{year}")
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> findDrownedWarshipWithWeaponByYear(@PathVariable int year) {
        return shipService.findDrownedWarshipWithWeaponByYear(year);
    }

    @PatchMapping("/warships_with_weapon/{id}")
    public ResponseEntity<DrownedRussianShipWithWeaponDTO> updateDrownedWarshipWithWeapon(@PathVariable int id, @RequestBody DrownedRussianShip drownedRussianShip) {
        return shipService.updateDrownedWarshipWithWeapon(id, drownedRussianShip);
    }

    @PostMapping("/warships_with_weapon")
    public void drownWarshipWithWeapon() {
    }

    @DeleteMapping("/warships_with_weapon/{id}")
    public ResponseEntity<List<DrownedRussianShipWithWeaponDTO>> deleteFromListOfDrownedShipsWithWeapon(@PathVariable int id) {
        return shipService.deleteFromListOfDrownedShipsWithWeapon(id);
    }

    //////////////////////////

    @GetMapping("/warships_with_previous_captains")
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findAllDrownedWarshipsWithPreviousCaptain() {
        return shipService.findAllDrownedWarshipsWithPreviousCaptain();
    }

    @GetMapping("/warships_with_previous_captains/{id}")
    public ResponseEntity<DrownedRussianShipWithPreviousCaptainDTO> findDrownedWarshipWithPreviousCaptainById(@PathVariable int id) {
        return shipService.findDrownedWarshipWithPreviousCaptainById(id);
    }

    @GetMapping("/warships_with_previous_captains/findByTonnage_{tonnage}")
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findDrownedWarshipWithPreviousCaptainByTonnage(@PathVariable int tonnage) {
        return shipService.findDrownedWarshipWithPreviousCaptainByTonnage(tonnage);
    }

    @GetMapping("/warships_with_previous_captains/findByName_{name}")
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findDrownedWarshipWithPreviousCaptainByName(@PathVariable String name) {
        return shipService.findDrownedWarshipWithPreviousCaptainByName(name);
    }

    @GetMapping("/warships_with_previous_captains/findByYear_{year}")
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> findDrownedWarshipWithPreviousCaptainByYear(@PathVariable int year) {
        return shipService.findDrownedWarshipWithPreviousCaptainByYear(year);
    }

    @PatchMapping("/warships_with_previous_captains/{id}")
    public ResponseEntity<DrownedRussianShipWithPreviousCaptainDTO> updateDrownedWarshipWithPreviousCaptain(@PathVariable int id, @RequestBody DrownedRussianShip drownedRussianShip) {
        return shipService.updateDrownedWarshipWithPreviousCaptain(id, drownedRussianShip);
    }

    @PostMapping("/warships_with_previous_captains")
    public void drownWarshipWithPreviousCaptain() {
    }

    @DeleteMapping("/warships_with_previous_captains/{id}")
    public ResponseEntity<List<DrownedRussianShipWithPreviousCaptainDTO>> deleteFromListOfDrownedShipsWithPreviousCaptains(@PathVariable int id) {
        return shipService.deleteFromListOfDrownedShipsWithPreviousCaptains(id);
    }

    ////////////////////////

    @GetMapping("/warships_with_captains")
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findAllDrownedWarshipsWithCaptain() {
        return shipService.findAllDrownedWarshipsWithCaptain();
    }

    @GetMapping("/warships_with_captains/{id}")
    public ResponseEntity<DrownedRussianShipWithCaptainDTO> findDrownedWarshipWithCaptainById(@PathVariable int id) {
        return shipService.findDrownedWarshipWithCaptainById(id);
    }

    @GetMapping("/warships_with_captains/findByTonnage_{tonnage}")
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByTonnage(@PathVariable int tonnage) {
        return shipService.findDrownedWarshipWithCaptainByTonnage(tonnage);
    }

    @GetMapping("/warships_with_captains/findByName_{name}")
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByName(@PathVariable String name) {
        return shipService.findDrownedWarshipWithCaptainByName(name);
    }

    @GetMapping("/warships_with_captains/findByYear_{year}")
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> findDrownedWarshipWithCaptainByYear(@PathVariable int year) {
        return shipService.findDrownedWarshipWithCaptainByYear(year);
    }

    @PatchMapping("/warships_with_captains/{id}")
    public ResponseEntity<DrownedRussianShipWithCaptainDTO> updateDrownedWarshipWithCaptain(@PathVariable int id, @RequestBody DrownedRussianShip drownedRussianShip) {
        return shipService.updateDrownedWarshipWithCaptain(id, drownedRussianShip);
    }

    @PostMapping("/warships_with_captains")
    public void drownWarshipWithCaptain() {
    }

    @DeleteMapping("/warships_with_captains/{id}")
    public ResponseEntity<List<DrownedRussianShipWithCaptainDTO>> deleteFromListOfDrownedShipsWithCaptains(@PathVariable int id) {
        return shipService.deleteFromListOfDrownedShipsWithCaptains(id);
    }

    ////////////////////////

    @GetMapping("/warships")
    public ResponseEntity<List<DrownedRussianShipDTO>> findAllDrownedWarships() {
        return shipService.findAllDrownedWarships();
    }

    @GetMapping("/warships/{id}")
    public ResponseEntity<DrownedRussianShipDTO> findDrownedWarshipById(@PathVariable int id) {
        return shipService.findDrownedWarshipById(id);
    }

    @GetMapping("/warships/findByName_{name}")
    public ResponseEntity<List<DrownedRussianShipDTO>> findByName(@PathVariable String name) {
        return shipService.findByName(name);
    }

    @GetMapping("/warships/findByYear_{year}")
    public ResponseEntity<List<DrownedRussianShipDTO>> findByYear(@PathVariable int year) {
        return shipService.findByYear(year);
    }

    @GetMapping("/warships/findByTonnage_{tonnage}")
    public ResponseEntity<List<DrownedRussianShipDTO>> findByTonnage(@PathVariable int tonnage) {
        return shipService.findByTonnage(tonnage);
    }

    @PatchMapping("/warships/{id}")
    public ResponseEntity<DrownedRussianShipDTO> updateDrownedWarship(@PathVariable int id, @RequestBody DrownedRussianShip drownedRussianShip) {
        return shipService.updateDrownedWarship(id, drownedRussianShip);
    }

    @PostMapping("/warships")
    public void drownWarship() {
    }

    @DeleteMapping("/warships/{id}")
    public ResponseEntity<List<DrownedRussianShipDTO>> deleteFromListOfDrownedShips(@PathVariable int id) {
        return shipService.deleteFromListOfDrownedShips(id);
    }
}
