package ua.project.java_advhomework.services.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.project.java_advhomework.dao.IPreviousCaptainDao;
import ua.project.java_advhomework.models.dto.PreviousCaptainDTO;
import ua.project.java_advhomework.models.entity.PreviousCaptain;
import ua.project.java_advhomework.services.IPreviousCaptainService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PreviousCaptainService implements IPreviousCaptainService {
    private IPreviousCaptainDao prevCaptainDao;

    @Override
    public ResponseEntity<List<PreviousCaptainDTO>> findAllPreviousCaptains() {
        List<PreviousCaptain> allCaptains = prevCaptainDao.findAll();
        List<PreviousCaptainDTO> allCaptainDTOS = allCaptains.stream().map(PreviousCaptainDTO::new).collect(Collectors.toList());
        log.info("All previous captains were found.");
        return new ResponseEntity<>(allCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PreviousCaptainDTO> findPreviousCaptainById(int id) {
        PreviousCaptain previousCaptain = prevCaptainDao.findById(id).orElse(new PreviousCaptain());
        if (previousCaptain.getId() == 0) {
            log.error("No content to show");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("Previous captain was found.");
        return new ResponseEntity<>(new PreviousCaptainDTO(previousCaptain), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PreviousCaptainDTO>> findByName(String name) {
        List<PreviousCaptain> captainsByName = prevCaptainDao.findByName(name);
        List<PreviousCaptainDTO> previousCaptainDTOS = captainsByName.stream().map(PreviousCaptainDTO::new).collect(Collectors.toList());
        log.info("All previous captains with appropriate name were found.");
        return new ResponseEntity<>(previousCaptainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PreviousCaptainDTO> updatePreviousCaptain(int id, PreviousCaptain captain) {
        if (prevCaptainDao.findAll().stream().allMatch(prevCaptainDao -> prevCaptainDao.getId() != id)) {
            log.error("There is no such id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PreviousCaptain captain1 = prevCaptainDao.findById(id).get();
        if (captain.getName() != null) {
            captain1.setName(captain.getName());
            prevCaptainDao.save(captain1);
            log.info("Previous captain was successfully updated.");
            return new ResponseEntity<>(new PreviousCaptainDTO(captain1), HttpStatus.OK);
        } else {
            log.error("Previous captain name is null!!!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<PreviousCaptainDTO>> savePreviousCaptainWithShip(PreviousCaptain previousCaptain) {
        if (previousCaptain != null) {
            prevCaptainDao.save(previousCaptain);
            log.info("Previous captain was successfully killed!!!!!!");
            return new ResponseEntity<>(prevCaptainDao.findAll().stream().map(PreviousCaptainDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("Previous captain is null!!!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<PreviousCaptainDTO>> deleteFromListOfPreviousCaptains(int id) {
        if (id != 0) {
            prevCaptainDao.deleteById(id);
            log.info("Previous captain was deleted from list");
            return new ResponseEntity<>(prevCaptainDao
                    .findAll()
                    .stream()
                    .map(PreviousCaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("Error. Id is 0!!!");
            return new ResponseEntity<>(prevCaptainDao
                    .findAll()
                    .stream()
                    .map(PreviousCaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
}
