package shubham.JobTracker.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {


    @GetMapping("/HealthCheck")
    public String healthCheck(){
        return  "Everything is okay";
    }
}
