package shubham.JobTracker.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import shubham.JobTracker.Service.UserDetailServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig {



    private  final JwtFilter jwtFilter;

     private final UserDetailServiceImpl userDetailService;

     public SecurityConfig(JwtFilter jwtFilter,UserDetailServiceImpl userDetailService){
         this.jwtFilter = jwtFilter;
         this.userDetailService = userDetailService;
     }

     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         http
                 .csrf(csrf -> csrf.disable())
                 .authorizeHttpRequests(auth -> auth
                         .requestMatchers("/check/**", "/Auth/**").permitAll()
                         .anyRequest().authenticated()
                 )
                 .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .authenticationProvider(authProvider());
         http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

         return http.build();


     }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

   @Bean
    public PasswordEncoder passwordEncoder(){
       return  new BCryptPasswordEncoder();
   }
}
