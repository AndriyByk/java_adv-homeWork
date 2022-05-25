package ua.project.java_advhomework.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.project.java_advhomework.models.dto.UserDTO;
import ua.project.java_advhomework.models.entity.tokens.AuthToken;
import ua.project.java_advhomework.models.entity.users.User;
import ua.project.java_advhomework.services.ITokenBuilderService;
import ua.project.java_advhomework.services.implementation.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private UserService userService;
    private ITokenBuilderService tokenBuilderService;

    public LoginFilter(String url, AuthenticationManager authenticationManager, UserService userService, ITokenBuilderService tokenBuilderService) {
        setFilterProcessesUrl(url);
        setAuthenticationManager(authenticationManager);
        this.userService = userService;
        this.tokenBuilderService = tokenBuilderService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws AuthenticationException {
        try {
            UserDTO userDTO = new ObjectMapper().readValue(servletRequest.getInputStream(), UserDTO.class);
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()/*, userDTO.getRoles()*/));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String jwtsToken = tokenBuilderService.getToken(authResult);
        User user = userService.findByName(authResult.getName());
        AuthToken token = new AuthToken();
        token.setPayload(jwtsToken);
        token.setUser(user);
        user.getAuthTokens().add(token);
        userService.save(user);
        servletResponse.addHeader("Authorization", "Bearer " + jwtsToken);
        chain.doFilter(servletRequest, servletResponse);
    }
}
