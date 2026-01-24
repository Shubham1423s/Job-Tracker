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

    @Size(min = 2, max = 200, message = "Job title must be between 2 and 200 characters")
    private String jobTitle;

    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String companyName;

    @Pattern(regexp = "^https?://.*", message = "Job link must be a valid URL")
    private String jobLink;


    private Status status;
    private Source source;
    private ApplicationMode applicationMode;
    private JobType jobType;

    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;


}
