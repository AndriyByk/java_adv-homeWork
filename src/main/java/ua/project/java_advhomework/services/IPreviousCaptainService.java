package ua.project.java_advhomework.services;

import org.springframework.http.ResponseEntity;
import ua.project.java_advhomework.models.dto.PreviousCaptainDTO;
import ua.project.java_advhomework.models.entity.PreviousCaptain;

import java.util.List;

public interface IPreviousCaptainService {
    ResponseEntity<List<PreviousCaptainDTO>> findAllPreviousCaptains();
    ResponseEntity<PreviousCaptainDTO> findPreviousCaptainById(int id);
    ResponseEntity<List<PreviousCaptainDTO>> findByName (String name);
    ResponseEntity<PreviousCaptainDTO> updatePreviousCaptain (int id, PreviousCaptain captain);
    ResponseEntity<List<PreviousCaptainDTO>> savePreviousCaptainWithShip (PreviousCaptain previousCaptain);
    ResponseEntity<List<PreviousCaptainDTO>> deleteFromListOfPreviousCaptains (int id);
}
