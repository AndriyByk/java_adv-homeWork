package ua.project.java_advhomework.services;

import org.springframework.http.ResponseEntity;
import ua.project.java_advhomework.models.dto.CaptainDTO;
import ua.project.java_advhomework.models.entity.Captain;

import java.util.List;

public interface ICaptainService {
    ResponseEntity<List<CaptainDTO>> getDeadCaptains();
    ResponseEntity<CaptainDTO> getDeadCaptainById (int id);
    ResponseEntity<List<CaptainDTO>> findByName (String name);
    ResponseEntity<CaptainDTO> updateDeadCaptain(int id, Captain captain);
    ResponseEntity<List<CaptainDTO>> killCaptain(Captain captain);
    ResponseEntity<List<CaptainDTO>> deleteFromListOfDeadCaptains (int id);
}
