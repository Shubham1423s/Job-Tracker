package shubham.JobTracker.Controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.context.ReactiveWebServerApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import shubham.JobTracker.Dto.ApplicationResponse;
import shubham.JobTracker.Dto.UserResponse;
import shubham.JobTracker.Entity.JobApplication;
import shubham.JobTracker.Entity.User;
import shubham.JobTracker.Service.JobApplicationService;
import shubham.JobTracker.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/User")
public class userController {

    @Autowired
    private UserService userService;

    @Autowired
   private JobApplicationService jobApplicationService;

    @GetMapping("/allApplication")
    public ResponseEntity<ApplicationResponse<List<JobApplication>>> allApplication(){
        List<JobApplication> list = jobApplicationService.allApllication();
        if(list != null && !list.isEmpty()) {
            return  ResponseEntity.status(HttpStatus.OK).body(new ApplicationResponse<>(list,"All Applications"));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body( new ApplicationResponse<>(null,"No application found"));

    }
    @PostMapping ("/saveApplication")
    public ResponseEntity<ApplicationResponse<JobApplication>> saveApplicaton(@RequestBody JobApplication application){
        jobApplicationService.saveApplication(application);
        return ResponseEntity.status(HttpStatus.CREATED).body( new ApplicationResponse<>(application,"Application Created successfully"));
    }

    @DeleteMapping("/deleteApplication/{id}")
    public ResponseEntity<ApplicationResponse<JobApplication>> deleteApplication(@PathVariable Integer id){
        jobApplicationService.deleteApplication(id);
        return ResponseEntity.status(HttpStatus.OK).body( new ApplicationResponse<>());
    }

    @PostMapping("/updateApplication/{id}")
    public ResponseEntity<ApplicationResponse<JobApplication>> updateApplication( @PathVariable  int id,@RequestBody JobApplication application){
        jobApplicationService.updateApplication(id,application);
        return  ResponseEntity.status(HttpStatus.OK).body(new ApplicationResponse<>(application,""));
    }


    @PostMapping("/updateUser")
    public ResponseEntity<UserResponse<User>> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return  ResponseEntity.status(HttpStatus.OK).body(new UserResponse<>());
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity<UserResponse<User>> deleteUser(@RequestBody User user){

        userService.deleteByUserName(user.getUserName());
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(new UserResponse<>());

    }

}
