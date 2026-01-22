package shubham.JobTracker.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shubham.JobTracker.Enums.ApplicationMode;
import shubham.JobTracker.Enums.JobType;
import shubham.JobTracker.Enums.Source;
import shubham.JobTracker.Enums.Status;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationResponse {

    private Long id;
    private String jobTitle;
    private String companyName;
    private String jobLink;
    private Status status;
    private Source source;
    private ApplicationMode applicationMode;
    private JobType jobtype;
    private LocalDate appliedAt;
    private LocalDate lastUpdated;
    private String note;




}
