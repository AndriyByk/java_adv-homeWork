package ua.project.java_advhomework.filters;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ua.project.java_advhomework.dao.IAuthTokenDAO;
import ua.project.java_advhomework.models.entity.tokens.AuthToken;
import ua.project.java_advhomework.models.entity.users.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestsFilter extends GenericFilterBean {
    private IAuthTokenDAO tokenDAO;

    public RequestsFilter(IAuthTokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String authorisationHeader = ((HttpServletRequest) servletRequest).getHeader("Authorisation");
        if (authorisationHeader != null && authorisationHeader.startsWith("Bearer ")) {
            String token = authorisationHeader.replace("Bearer ", "");
            AuthToken authToken = tokenDAO.findAuthTokenByPayload(token);
            User tokenUser = authToken.getUser();
            if (tokenUser != null){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        tokenUser.getUsername(),
                        tokenUser.getPassword(),
                        tokenUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
