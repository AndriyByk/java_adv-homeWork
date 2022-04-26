package ua.project.java_advhomework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.project.java_advhomework.dao.IPreviousCaptainDao;
import ua.project.java_advhomework.models.dto.PreviousCaptainDTO;
import ua.project.java_advhomework.models.entity.PreviousCaptain;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/previous_captains")
@AllArgsConstructor
public class PreviousCaptainController {
    private IPreviousCaptainDao prevCaptainDao;

    @GetMapping("")
    public ResponseEntity<List<PreviousCaptainDTO>> findAllPreviousCaptains() {
        List<PreviousCaptain> allCaptains = prevCaptainDao.findAll();
        List<PreviousCaptainDTO> allCaptainDTOS = allCaptains.stream().map(PreviousCaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(allCaptainDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreviousCaptainDTO> findPreviousCaptainById(@PathVariable int id) {
        PreviousCaptain previousCaptain = prevCaptainDao.findById(id).orElse(new PreviousCaptain());
        if (previousCaptain.getId() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new PreviousCaptainDTO(previousCaptain), HttpStatus.OK);
    }

    @GetMapping("/findByName_{name}")
    public ResponseEntity<List<PreviousCaptainDTO>> findByName (@PathVariable String name) {
        List<PreviousCaptain> captainsByName = prevCaptainDao.findByName(name);
        List<PreviousCaptainDTO> previousCaptainDTOS = captainsByName.stream().map(PreviousCaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(previousCaptainDTOS, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PreviousCaptainDTO> updatePreviousCaptain (@PathVariable int id, @RequestBody PreviousCaptain captain) {
        PreviousCaptain captain1 = prevCaptainDao.findById(id).get();
        if (captain.getName() != null) {
            captain1.setName(captain.getName());
            prevCaptainDao.save(captain1);
            return new ResponseEntity<>(new PreviousCaptainDTO(captain1), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    public ResponseEntity<List<PreviousCaptainDTO>> savePreviousCaptainWithShip (@RequestBody PreviousCaptain previousCaptain) {
        if (previousCaptain != null) {
            prevCaptainDao.save(previousCaptain);
            return new ResponseEntity<>(prevCaptainDao.findAll().stream().map(PreviousCaptainDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<PreviousCaptainDTO>> deleteFromListOfPreviousCaptains (@PathVariable int id) {
        if (id != 0) {
            prevCaptainDao.deleteById(id);
            return new ResponseEntity<>(prevCaptainDao
                    .findAll()
                    .stream()
                    .map(PreviousCaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(prevCaptainDao
                    .findAll()
                    .stream()
                    .map(PreviousCaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
}
