package shubham.JobTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shubham.JobTracker.Entity.JobApplication;
import shubham.JobTracker.Entity.User;

import java.util.List;

public interface JobApplicationRepo extends JpaRepository<JobApplication,Integer> {
    List<JobApplication> findByUser(User user);


}
