package javaweb.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javaweb.Handler.AuthenticationSuccessHandler;
import javaweb.Handler.CustomAuthenticationFailureHandler;
import javaweb.Jwt.JwtAuthenticationFilter;
import javaweb.Service.MyUserService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	@Lazy
	private MyUserService userDetailService; 
	
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter; 
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                	registry.requestMatchers("/scss/**", "/js/**", "/img/**", "/lib/**", "/webjars/**", "/static/**","/api/**", "/product", "/login").permitAll();
                    registry.requestMatchers("/admin/**").hasRole("ADMIN");
                    registry.requestMatchers("/user/**").hasRole("USER");
                    registry.anyRequest().authenticated();
                })
                
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                    .loginPage("/login")
                    .successHandler(new AuthenticationSuccessHandler())
                    .failureHandler(new CustomAuthenticationFailureHandler())
                    .permitAll();
                })
                .logout(logoutConfigurer -> {
                    logoutConfigurer
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login") 
                    .invalidateHttpSession(true) 
                    .deleteCookies("JSESSIONID") 
                    .permitAll();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) 
                .build();
    }
	

	@Bean
	public UserDetailsService userDetailService() {
		return userDetailService;
	}
	
	@Bean 
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	
}
