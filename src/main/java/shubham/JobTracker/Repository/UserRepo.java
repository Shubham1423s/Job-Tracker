package shubham.JobTracker.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import shubham.JobTracker.Entity.User;

import javax.sql.rowset.spi.SyncResolver;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUserName(String username);
    User deleteByUserName(String username);



}
