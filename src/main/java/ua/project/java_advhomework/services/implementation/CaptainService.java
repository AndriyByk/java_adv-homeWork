package ua.project.java_advhomework.services.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.project.java_advhomework.dao.ICaptainDao;
import ua.project.java_advhomework.models.dto.CaptainDTO;
import ua.project.java_advhomework.models.entity.Captain;
import ua.project.java_advhomework.services.ICaptainService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CaptainService implements ICaptainService {
    private ICaptainDao captainDAO;

    @Override
    public ResponseEntity<List<CaptainDTO>> getDeadCaptains() {
        return new ResponseEntity<>(captainDAO
                .findAll()
                .stream()
                .map(CaptainDTO::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CaptainDTO> getDeadCaptainById(int id) {
        Captain captain = captainDAO.findById(id).orElse(new Captain());
        if (captain.getId() == 0) {
            log.error("No content to show");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CaptainDTO(captain), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CaptainDTO>> findByName(String name) {
        List<Captain> captainsByName = captainDAO.findByName(name);
        List<CaptainDTO> captainDTOS = captainsByName.stream().map(CaptainDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(captainDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CaptainDTO> updateDeadCaptain(int id, Captain captain) {
        if (captainDAO.findAll().stream().allMatch(captainDao -> captainDao.getId() != id)) {
            log.error("There is no such id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Captain captain1 = captainDAO.findById(id).get();
        if (captain.getName() != null) {
            captain1.setName(captain.getName());
            captainDAO.save(captain1);
            return new ResponseEntity<>(new CaptainDTO(captain1), HttpStatus.OK);
        } else {
            log.error("Captain name is null!!!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<CaptainDTO>> killCaptain(Captain captain) {
        if (captain != null) {
            captainDAO.save(captain);
            return new ResponseEntity<>(captainDAO.findAll().stream().map(CaptainDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("Captain is null!!!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<CaptainDTO>> deleteFromListOfDeadCaptains(int id) {
        if (id != 0) {
            captainDAO.deleteById(id);
            return new ResponseEntity<>(captainDAO
                    .findAll()
                    .stream()
                    .map(CaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            log.error("Error. Id is 0!!!");
            return new ResponseEntity<>(captainDAO
                    .findAll()
                    .stream()
                    .map(CaptainDTO::new)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
}
