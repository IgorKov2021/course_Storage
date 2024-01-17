package com.example.course_storage.config;

import com.example.course_storage.model.Role;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class AppSecurityFilter {

    /*@Bean
    public static PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }*/

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {


        http
                //.anonymous().disable()
                //  .httpBasic().disable()
                .authorizeRequests(authorize -> authorize
                                .requestMatchers(HttpMethod.GET, "/good").permitAll()
                                .requestMatchers(HttpMethod.POST, "/good").permitAll()
                                .requestMatchers(HttpMethod.GET, "/test").permitAll()
                                .requestMatchers(HttpMethod.POST, "/test").permitAll()
                                .requestMatchers(HttpMethod.GET, "/start").permitAll()
                                .requestMatchers("/change/person").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/start").permitAll()

                                .requestMatchers("/login/good").permitAll()
                                .requestMatchers("/hello").authenticated()
                                .requestMatchers(HttpMethod.POST,"/static/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/static/**").permitAll()

                     //.anyRequest().authenticated()
                       // .anyRequest().notifyAll()

                )
                .formLogin(form -> form
                        .loginPage("/start")
                        .loginProcessingUrl("/mylogin")
                        .usernameParameter("user")
                        .passwordParameter("pass")
                        .successHandler((request, response, authentication) -> {
                            response.sendRedirect("/start");

                        })
                      //.failureHandler((request, response, exception) -> {
                       //   String message = exception.getMessage();
                          //    response.sendRedirect("/");
                            //exception.getMessage();
                          //String email = request.getParameter("user");
                         // String error = exception.getMessage();
                         // System.out.println("A failed login attempt with email: "
                            //      + email + ". Reason: " + error);
                         // response.addCookie(new Cookie("sd", email));

                      //    String redirectUrl = request.getContextPath() + "/mylogin?error";
                         // response.sendRedirect("/");
                     //   })
                )


                .cors().disable()
                .csrf().disable()
                /*.logout((logout) ->
                        logout.logoutSuccessUrl("/good")

                                )
                .formLogin(withDefaults())*/
                /* .logout(
                         logout -> logout
                                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout/my"))
                                 .permitAll()
                 )*/
                // .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutUrl("/log"))
                .logout(logout ->
                        logout.logoutUrl("/mylogout")
                                .logoutSuccessHandler((request, response, authentication) -> {
                                            request.getSession().invalidate();
                                            response.sendRedirect("/start");
                                        }


                                )
                )

                //.anonymous().disable()
                .httpBasic(withDefaults());

        return http.build();


    }

}
