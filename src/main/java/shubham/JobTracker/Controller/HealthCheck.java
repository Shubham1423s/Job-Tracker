package shubham.JobTracker.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class HealthCheck {


    @GetMapping("/HealthCheck")
    public String healthCheck(){
        return  "Everything is okay";
    }

    @GetMapping("/current-User")
    public ResponseEntity<String> currentUser(){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if(name != null){
            return ResponseEntity.ok("Current User   - " + name);
        }
        return ResponseEntity.ok("");


    }
}
