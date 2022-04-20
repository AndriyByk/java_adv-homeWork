package ua.project.java_advhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;

import java.util.List;

public interface IRussianShipDao extends JpaRepository <DrownedRussianShip, Integer> {
    List<DrownedRussianShip> findByName(String name);
    List<DrownedRussianShip> findByYear(int year);
    List<DrownedRussianShip> findByTonnage(int tonnage);
}
