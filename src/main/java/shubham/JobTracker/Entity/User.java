package shubham.JobTracker.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Table(name = "users")
@Entity
public class User {

    @Id
    private int id;

    private String userName;
    private String password;
    private String  email;

}
