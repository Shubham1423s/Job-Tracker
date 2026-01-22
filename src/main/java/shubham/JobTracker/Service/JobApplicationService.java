package shubham.JobTracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import shubham.JobTracker.Dto.Request.CreateJobApplicationRequest;
import shubham.JobTracker.Dto.Request.UpdateJobApplicationRequest;
import shubham.JobTracker.Dto.Response.JobApplicationResponse;
import shubham.JobTracker.Dto.Response.JobStatsResponse;
import shubham.JobTracker.Entity.JobApplication;
import shubham.JobTracker.Entity.User;
import shubham.JobTracker.Repository.JobApplicationRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static shubham.JobTracker.Enums.Status.*;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepo jobApplicationRepo;
    @Autowired
    private  UserService userService;

    public List<JobApplicationResponse> allApllication(){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(name);
        List<JobApplication> applications  = jobApplicationRepo.findByUser(user);
        return applications.stream()
                .map(this::mapToResponse)
                .toList();
    }
    public JobApplicationResponse saveApplication(CreateJobApplicationRequest request){

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(userName);

        JobApplication application = new JobApplication();
        application.setJobTitle(request.getJobTitle());
        application.setCompanyName(request.getCompanyName());
        application.setJobLink(request.getJobLink());
        application.setStatus(request.getStatus());
        application.setSource(request.getSource());
        application.setApplicationMode(request.getApplicationMode());
        application.setJobtype(request.getJobType());
        application.setNote(request.getNote());

        application.setAppliedAt(LocalDate.now());
        application.setLastUpdated(LocalDate.now());

        application.setUser(user);
       JobApplication saved =   jobApplicationRepo.save(application);
       return mapToResponse(saved);


    }
    private JobApplicationResponse mapToResponse(JobApplication application) {

        JobApplicationResponse response = new JobApplicationResponse();
        response.setId(application.getId());
        response.setJobTitle(application.getJobTitle());
        response.setCompanyName(application.getCompanyName());
        response.setJobLink(application.getJobLink());
        response.setStatus(application.getStatus());
        response.setSource(application.getSource());
        response.setApplicationMode(application.getApplicationMode());
        response.setJobtype(application.getJobtype());
        response.setAppliedAt(application.getAppliedAt());
        response.setLastUpdated(application.getLastUpdated());
        response.setNote(application.getNote());

        return response;
    }

    public  void deleteApplication(int id){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(username);

        JobApplication job = jobApplicationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job application not found"));

        // user ownership check (important)
        if(!job.getUser().equals(user.getId())){
            throw new RuntimeException("Unauthorized delete attempt");
        }


        jobApplicationRepo.delete(job);

    }

    public JobApplicationResponse updateApplication(int id, UpdateJobApplicationRequest request) {

       JobApplication existing =  jobApplicationRepo.findById(id)
               .orElseThrow(() -> new RuntimeException("Job Application Not Found"));

        if (request.getJobTitle() != null) existing.setJobTitle(request.getJobTitle());
        if (request.getCompanyName() != null) existing.setCompanyName(request.getCompanyName());
        if (request.getJobLink() != null) existing.setJobLink(request.getJobLink());
        if (request.getStatus() != null) existing.setStatus(request.getStatus());
        if (request.getSource() != null) existing.setSource(request.getSource());
        if (request.getApplicationMode() != null) existing.setApplicationMode(request.getApplicationMode());
        if (request.getJobType() != null) existing.setJobtype(request.getJobType());
        if (request.getNote() != null) existing.setNote(request.getNote());

        // Always update timestamp
        existing.setLastUpdated(LocalDate.now());

        JobApplication saved = jobApplicationRepo.save(existing);

        return mapToResponse(saved);
    }

        public JobStatsResponse getStats(User user) {
        List<JobApplication> applications  = jobApplicationRepo.findByUser(user);

        JobStatsResponse response = new JobStatsResponse();
            response.setTotal(applications.size());

            response.setApplied(applications.stream().filter(a -> a.getStatus() == APPLIED).count());
            response.setInterview(applications.stream().filter(a -> a.getStatus() == INTERVIEW).count());
            response.setOffer(applications.stream().filter(a -> a.getStatus() == OFFER).count());
            response.setRejected(applications.stream().filter(a -> a.getStatus() == REJECTED).count());

            return response;
        }

}
