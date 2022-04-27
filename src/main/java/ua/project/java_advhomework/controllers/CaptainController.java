package ua.project.java_advhomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.project.java_advhomework.dao.ICaptainDao;
import ua.project.java_advhomework.models.dto.CaptainDTO;
import ua.project.java_advhomework.models.entity.Captain;
import ua.project.java_advhomework.services.ICaptainService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/captains")
public class CaptainController {
    private ICaptainService captainService;

    @GetMapping("")
    public ResponseEntity<List<CaptainDTO>> getDeadCaptains() {
        return captainService.getDeadCaptains();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaptainDTO> getDeadCaptainById (@PathVariable int id) {
        return captainService.getDeadCaptainById(id);
    }

    @GetMapping("/findByName_{name}")
    public ResponseEntity<List<CaptainDTO>> findByName (@PathVariable String name) {
        return captainService.findByName(name);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CaptainDTO> updateDeadCaptain(@PathVariable int id, @RequestBody Captain captain) {
        return captainService.updateDeadCaptain(id, captain);
    }

    @PostMapping("")
    public ResponseEntity<List<CaptainDTO>> killCaptain(@RequestBody Captain captain) {
        return captainService.killCaptain(captain);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<CaptainDTO>> deleteFromListOfDeadCaptains (@PathVariable int id) {
        return captainService.deleteFromListOfDeadCaptains(id);
    }
}
