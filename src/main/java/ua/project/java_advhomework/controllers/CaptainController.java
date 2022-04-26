package ua.project.java_advhomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.project.java_advhomework.dao.ICaptainDao;
import ua.project.java_advhomework.models.dto.CaptainDTO;
import ua.project.java_advhomework.models.entity.Captain;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/captains")
public class CaptainController {
    private ICaptainDao captainDAO;

    @GetMapping("")
    public ResponseEntity<List<CaptainDTO>> getDeadCaptains() {
        return new ResponseEntity<>(captainDAO
                .findAll()
                .stream()
                .map(CaptainDTO::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaptainDTO> getDeadCaptainById (@PathVariable int id) {
        Captain captain = captainDAO.findById(id).orElse(new Captain());
        if (captain.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CaptainDTO(captain), HttpStatus.OK);
    }

    @GetMapping("/findByName_{name}")
    public ResponseEntity<List<CaptainDTO>> findByName (@PathVariable String name) {
        List<Captain> captainsByName = captainDAO.findByName(name);
        List<CaptainDTO> captainDTOS = captainsByName.stream().map(CaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(captainDTOS, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CaptainDTO> updateDeadCaptain(@PathVariable int id, @RequestBody Captain captain) {
        Captain captain1 = captainDAO.findById(id).get();
        if (captain.getName() != null) {
            captain1.setName(captain.getName());
            captainDAO.save(captain1);
            return new ResponseEntity<>(new CaptainDTO(captain1), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    public ResponseEntity<List<CaptainDTO>> killCaptain(@RequestBody Captain captain) {
        if (captain != null) {
            captainDAO.save(captain);
            return new ResponseEntity<>(captainDAO.findAll().stream().map(CaptainDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<CaptainDTO>> deleteFromListOfDeadCaptains (@PathVariable int id) {
        if (id != 0) {
            captainDAO.deleteById(id);
            return new ResponseEntity<>(captainDAO
                    .findAll()
                    .stream()
                    .map(CaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(captainDAO
                    .findAll()
                    .stream()
                    .map(CaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
}
