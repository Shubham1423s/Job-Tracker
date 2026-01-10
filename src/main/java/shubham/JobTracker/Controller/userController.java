package shubham.JobTracker.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @GetMapping("/HealthCheck")
    public String callMe(){
      return " hey local host" ;
    }


}
