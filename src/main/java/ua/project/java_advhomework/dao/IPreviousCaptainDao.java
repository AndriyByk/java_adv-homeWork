package ua.project.java_advhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.project.java_advhomework.models.entity.PreviousCaptain;

import java.util.List;

public interface IPreviousCaptainDao extends JpaRepository <PreviousCaptain, Integer> {
    List<PreviousCaptain> findByName(String name);
}
