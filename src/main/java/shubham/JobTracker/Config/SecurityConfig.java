package shubham.JobTracker.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import shubham.JobTracker.Service.UserDetailServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


     private final UserDetailServiceImpl userDetailService;

     public SecurityConfig(UserDetailServiceImpl userDetailService){
         this.userDetailService = userDetailService;
     }

     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         http
                 .csrf(csrf -> csrf.disable())
                 .authorizeHttpRequests(auth -> auth
                         .requestMatchers("/HealthCheck","/public/**").permitAll()
                         .anyRequest().authenticated()
                 )
                 .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                 .httpBasic(Customizer.withDefaults());

                 return http.build();

     }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);

        builder
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
        return builder.build();

    }

   @Bean
    public PasswordEncoder passwordEncoder(){
       return  new BCryptPasswordEncoder();
   }
}
