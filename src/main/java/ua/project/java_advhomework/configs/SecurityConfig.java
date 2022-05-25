package ua.project.java_advhomework.configs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import ua.project.java_advhomework.dao.IAuthTokenDAO;
import ua.project.java_advhomework.filters.LoginFilter;
import ua.project.java_advhomework.filters.RequestsFilter;
import ua.project.java_advhomework.services.ITokenBuilderService;
import ua.project.java_advhomework.services.implementation.UserService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private CorsConfigurationSource corsConfigurationSource;
    private DaoAuthenticationProvider daoAuthenticationProvider;
    private UserService userService;
    private IAuthTokenDAO tokenDAO;
    private ITokenBuilderService tokenBuilderService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(corsConfigurationSource).and()
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/warships_with_all_components",
                        "/warships_with_weapon",
                        "/warships_with_previous_captains",
                        "/warships_with_captains",
                        "/warships",
                        "/weapons",
                        "/previous_captains",
                        "/captains").permitAll()
                .antMatchers(HttpMethod.POST, "/save", "/login").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/warships/{id}",
                        "/warships/findByTonnage_{tonnage}",
                        "/warships/findByName_{name}",
                        "/warships/findByYear_{year}",

                        "/warships_with_all_components/{id}",
                        "/warships_with_all_components/findByTonnage_{tonnage}",
                        "/warships_with_all_components/findByName_{name}",
                        "/warships_with_all_components/findByYear_{year}",

                        "/warships_with_weapon/{id}",
                        "/warships_with_weapon/findByTonnage_{tonnage}",
                        "/warships_with_weapon/findByName_{name}",
                        "/warships_with_weapon/findByYear_{year}",

                        "/warships_with_previous_captains/{id}",
                        "/warships_with_previous_captains/findByTonnage_{tonnage}",
                        "/warships_with_previous_captains/findByName_{name}",
                        "/warships_with_previous_captains/findByYear_{year}",

                        "/warships_with_captains/{id}",
                        "/warships_with_captains/findByTonnage_{tonnage}",
                        "/warships_with_captains/findByName_{name}",
                        "/warships_with_captains/findByYear_{year}",

                        "/captains/{id}",
                        "/captains/findByName_{name}",

                        "/previous_captains/{id}",
                        "/previous_captains/findByName_{name}",

                        "/weapons/{id}",
                        "/weapons/findByCode_{code}").hasAnyRole("ROLE_MILITARY", "ROLE_CIVIL")
                .antMatchers(HttpMethod.PATCH,
                        "/warships_with_all_components/{id}",
                        "/warships_with_weapon/{id}",
                        "/warships_with_previous_captains/{id}",
                        "/warships_with_captains/{id}",
                        "/warships/{id}",
                        "/captains/{id}",
                        "/previous_captains/{id}",
                        "/weapons/{id}").hasRole("ROLE_MILITARY")
                .antMatchers(HttpMethod.POST,
                        "/warships_with_all_components",
                        "/warships_with_weapon",
                        "/warships_with_previous_captains",
                        "/warships_with_captains",
                        "/warships",
                        "/captains",
                        "/previous_captains",
                        "/weapons").hasRole("ROLE_MILITARY")
                .antMatchers(HttpMethod.DELETE,
                        "/warships_with_all_components/{id}",
                        "/warships_with_weapon/{id}",
                        "/warships_with_previous_captains/{id}",
                        "/warships_with_captains/{id}",
                        "/warships/{id}",
                        "/captains/{id}",
                        "/previous_captains/{id}",
                        "/weapons/{id}").hasRole("ROLE_MILITARY").and()
                .addFilterBefore(new LoginFilter("/login", authenticationManager(), userService, tokenBuilderService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new RequestsFilter(tokenDAO), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider);
    }
}
