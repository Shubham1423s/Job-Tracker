package shubham.JobTracker.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shubham.JobTracker.Dto.Response.JobStatsResponse;
import shubham.JobTracker.Entity.User;
import shubham.JobTracker.Service.JobApplicationService;
import shubham.JobTracker.Service.UserService;

@RestController
@RequestMapping("/DashBoard")
public class DashBoardController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private UserService userService;

    @GetMapping("/Stats")
    public ResponseEntity<JobStatsResponse> stats(){

        String userName= SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(userName);

       JobStatsResponse response=  jobApplicationService.getStats(user);
       return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
