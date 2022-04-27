package ua.project.java_advhomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.project.java_advhomework.models.dto.PreviousCaptainDTO;
import ua.project.java_advhomework.models.entity.PreviousCaptain;
import ua.project.java_advhomework.services.IPreviousCaptainService;

import java.util.List;

@RestController
@RequestMapping("/previous_captains")
@AllArgsConstructor
public class PreviousCaptainController {
    private IPreviousCaptainService previousCaptainService;

    @GetMapping("")
    public ResponseEntity<List<PreviousCaptainDTO>> findAllPreviousCaptains() {
        return previousCaptainService.findAllPreviousCaptains();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreviousCaptainDTO> findPreviousCaptainById(@PathVariable int id) {
        return previousCaptainService.findPreviousCaptainById(id);
    }

    @GetMapping("/findByName_{name}")
    public ResponseEntity<List<PreviousCaptainDTO>> findByName (@PathVariable String name) {
        return previousCaptainService.findByName(name);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PreviousCaptainDTO> updatePreviousCaptain (@PathVariable int id, @RequestBody PreviousCaptain captain) {
        return previousCaptainService.updatePreviousCaptain(id, captain);
    }

    @PostMapping("")
    public ResponseEntity<List<PreviousCaptainDTO>> savePreviousCaptainWithShip (@RequestBody PreviousCaptain previousCaptain) {
        return previousCaptainService.savePreviousCaptainWithShip(previousCaptain);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<PreviousCaptainDTO>> deleteFromListOfPreviousCaptains (@PathVariable int id) {
        return previousCaptainService.deleteFromListOfPreviousCaptains(id);
    }
}
