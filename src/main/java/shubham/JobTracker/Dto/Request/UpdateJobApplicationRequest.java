package shubham.JobTracker.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import shubham.JobTracker.Enums.ApplicationMode;
import shubham.JobTracker.Enums.JobType;
import shubham.JobTracker.Enums.Source;
import shubham.JobTracker.Enums.Status;
@Getter
@Setter
public class UpdateJobApplicationRequest {

    private String jobTitle;
    private String companyName;
    private String jobLink;
    private Status status;
    private Source source;
    private ApplicationMode applicationMode;
    private JobType jobType;
    private String note;


}
