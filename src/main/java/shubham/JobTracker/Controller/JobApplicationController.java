package shubham.JobTracker.Controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shubham.JobTracker.Dto.ApplicationResponse;
import shubham.JobTracker.Entity.JobApplication;
import shubham.JobTracker.Service.JobApplicationService;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/Application")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @GetMapping("/allApplication")
    public ResponseEntity<ApplicationResponse<List<JobApplication>>> allApplication(){

      List<JobApplication> applicationList =   jobApplicationService.allApllication();
      if( applicationList != null && !applicationList.isEmpty() ){
          return ResponseEntity.status(HttpStatus.OK).body(new ApplicationResponse<>(applicationList,"All Applications"));

      }
        return ResponseEntity.status(HttpStatus.OK).body(new ApplicationResponse<>(null," No Application Found"));



    }
    @PostMapping("/saveApplication")
    public ResponseEntity<ApplicationResponse<JobApplication>> saveApplication(@RequestBody JobApplication jobApplication){
        jobApplicationService.saveApplication(jobApplication);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApplicationResponse<>(jobApplication,"Application Saved "));

    }


}
