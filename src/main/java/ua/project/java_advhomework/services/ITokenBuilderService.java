package ua.project.java_advhomework.services;

import org.springframework.security.core.Authentication;

public interface ITokenBuilderService {
    String getToken(Authentication authResult);
}
