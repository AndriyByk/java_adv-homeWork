package ua.project.java_advhomework.services.implementation;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.project.java_advhomework.dao.UserDAO;
import ua.project.java_advhomework.models.entity.users.User;
import ua.project.java_advhomework.services.IUserService;

@Service
@AllArgsConstructor
public class UserService implements IUserService, UserDetailsService {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByUsername(username);
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public User findByName(String name) {
        return userDAO.findByUsername(name);
    }
}
