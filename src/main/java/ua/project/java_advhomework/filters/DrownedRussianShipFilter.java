package ua.project.java_advhomework.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ua.project.java_advhomework.models.dto.DrownedRussianShipDTO;
import ua.project.java_advhomework.models.entity.DrownedRussianShip;
import ua.project.java_advhomework.services.IDrownedShipService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Component
@Order(1)
public class DrownedRussianShipFilter implements Filter {
    private IDrownedShipService shipService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestMethod = httpServletRequest.getMethod();
        String requestURI = httpServletRequest.getRequestURI();
        String[] partsOfURI = requestURI.split("/");
//        if (requestMethod.equals("POST")) {
//            DrownedRussianShip ship = new ObjectMapper().readValue(httpServletRequest.getInputStream(), DrownedRussianShip.class);
//            switch (partsOfURI[1]) {
//                case "warships": {
//                    shipService.drownWarship(ship);
//                    break;
//                }
//                case "warships_with_captains": {
//                    shipService.drownWarshipWithCaptain(ship);
//                    break;
//                }
//                case "warships_with_previous_captains": {
//                    shipService.drownWarshipWithPreviousCaptain(ship);
//                    break;
//                }
//                case "warships_with_weapon": {
//                    shipService.drownWarshipWithWeapon(ship);
//                    break;
//                }
//                case "warships_with_all_components": {
//                    shipService.drownWarshipWithAllComponents(ship);
//                }
//            }
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
