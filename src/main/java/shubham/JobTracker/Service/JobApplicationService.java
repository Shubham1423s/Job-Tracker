package shubham.JobTracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shubham.JobTracker.Entity.JobApplication;
import shubham.JobTracker.Repository.JobApplicationRepo;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepo jobApplicationRepo;

    public List<JobApplication> allApllication(){

        return jobApplicationRepo.findAll();
    }
    public void saveApplication(JobApplication jobApplication){
        jobApplicationRepo.save(jobApplication);
    }
    public  void deleteApplication(JobApplication jobApplication){
        jobApplicationRepo.delete(jobApplication);

    }
//    public Optional<JobApplication> getApplication(int id){
//        return id;
//    }
    public JobApplication updateApplication(JobApplication jobApplication){
        return jobApplication;

    }
}
