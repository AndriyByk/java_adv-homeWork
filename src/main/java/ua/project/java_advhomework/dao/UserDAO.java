package ua.project.java_advhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.project.java_advhomework.models.entity.users.User;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
