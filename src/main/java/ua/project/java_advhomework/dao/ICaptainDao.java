package ua.project.java_advhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.project.java_advhomework.models.entity.Captain;

import java.util.List;

public interface ICaptainDao extends JpaRepository<Captain, Integer> {
    List<Captain> findByName(String name);
}
