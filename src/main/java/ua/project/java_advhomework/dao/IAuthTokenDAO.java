package ua.project.java_advhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.project.java_advhomework.models.entity.tokens.AuthToken;

public interface IAuthTokenDAO extends JpaRepository<AuthToken, Integer> {
    AuthToken findAuthTokenByPayload(String token);
}
