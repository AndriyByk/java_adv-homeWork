package ua.project.java_advhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.project.java_advhomework.models.entity.Weapon;

import java.util.List;

public interface IWeaponDao extends JpaRepository<Weapon, Integer> {
    List<Weapon> findByCode(String code);
}
