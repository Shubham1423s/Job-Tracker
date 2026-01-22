package shubham.JobTracker.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import shubham.JobTracker.Enums.ApplicationMode;
import shubham.JobTracker.Enums.JobType;
import shubham.JobTracker.Enums.Source;
import shubham.JobTracker.Enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Table(name = "job_applications")
@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;

    private  String companyName;

    private String jobLink;

    private Status status;

    private LocalDate appliedAt;

    private LocalDate lastUpdated;

    private Source source;

    private ApplicationMode applicationMode;

    private JobType jobtype;

    @Column(length = 1000)
    private String  note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
     private User user;



}
