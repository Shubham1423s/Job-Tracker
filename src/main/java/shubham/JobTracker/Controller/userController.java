package shubham.JobTracker.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shubham.JobTracker.Dto.Request.CreateJobApplicationRequest;
import shubham.JobTracker.Dto.Request.UpdateJobApplicationRequest;
import shubham.JobTracker.Dto.Request.UpdateUserRequest;
import shubham.JobTracker.Dto.Response.JobApplicationResponse;
import shubham.JobTracker.Dto.Response.UserResponse;
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
    public ResponseEntity<List<JobApplicationResponse>> allApplication(){
        List<JobApplicationResponse> list = jobApplicationService.allApllication();

        return ResponseEntity.status(HttpStatus.OK).body(list);

    }
    @PostMapping ("/saveApplication")
    public ResponseEntity<JobApplicationResponse> saveApplicaton(@Valid  @RequestBody CreateJobApplicationRequest application){

        JobApplicationResponse response =  jobApplicationService.saveApplication(application);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @DeleteMapping("/deleteApplication/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Integer id){
        jobApplicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/updateApplication/{id}")
    public ResponseEntity<JobApplicationResponse> updateApplication(@PathVariable  int id, @RequestBody UpdateJobApplicationRequest request){
        JobApplicationResponse response =  jobApplicationService.updateApplication(id,request);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest request){
       UserResponse response =  userService.updateUser(request);
        return  ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestBody User user){

        userService.deleteByUserName(user.getUserName());
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
