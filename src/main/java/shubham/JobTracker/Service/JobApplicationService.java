package shubham.JobTracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import shubham.JobTracker.Entity.JobApplication;
import shubham.JobTracker.Entity.User;
import shubham.JobTracker.Repository.JobApplicationRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepo jobApplicationRepo;
    @Autowired
    private  UserService userService;

    public List<JobApplication> allApllication(){

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(name);
        List<JobApplication> applications  = jobApplicationRepo.findByUser(user);
        return applications;
    }
    public void saveApplication(JobApplication jobApplication){
        String userName  = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(userName);
        jobApplication.setUser(user);
        jobApplicationRepo.save(jobApplication);
    }

    public  void deleteApplication(int id){
        Optional<JobApplication> jobApplication = jobApplicationRepo.findById(id);
        jobApplicationRepo.delete(jobApplication.get());

    }

    public boolean updateApplication(int id,JobApplication jobApplication){

        Optional<JobApplication> application = jobApplicationRepo.findById(id);

        if (application.isEmpty()) {
            return false;
        }

        JobApplication existingApplication = application.get();


        if (jobApplication.getJobTitle() != null) {
            existingApplication.setJobTitle(jobApplication.getJobTitle());
        }

        if (jobApplication.getCompanyName() != null) {
            existingApplication.setCompanyName(jobApplication.getCompanyName());
        }

        if (jobApplication.getJobLink() != null) {
            existingApplication.setJobLink(jobApplication.getJobLink());
        }

        if (jobApplication.getStatus() != null) {
            existingApplication.setStatus(jobApplication.getStatus());
        }

        if (jobApplication.getAppliedAt() != null) {
            existingApplication.setAppliedAt(jobApplication.getAppliedAt());
        }

        if (jobApplication.getSource() != null) {
            existingApplication.setSource(jobApplication.getSource());
        }

        if (jobApplication.getApplicationMode() != null) {
            existingApplication.setApplicationMode(jobApplication.getApplicationMode());
        }

        if (jobApplication.getJobtype() != null) {
            existingApplication.setJobtype(jobApplication.getJobtype());
        }

        if (jobApplication.getNote() != null) {
            existingApplication.setNote(jobApplication.getNote());
        }

        existingApplication.setLastUpdated(LocalDate.now());

        jobApplicationRepo.save(existingApplication);

        return true;


    }
}
