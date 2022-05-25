package ua.project.java_advhomework.services;

import ua.project.java_advhomework.models.entity.users.User;

public interface IUserService {
    void save(User user);
    User findByName(String name);
}
