package shubham.JobTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shubham.JobTracker.Entity.JobApplication;

public interface JobApplicationRepo extends JpaRepository<JobApplication,Integer> {


}
