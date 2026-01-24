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
public class CreateJobApplicationRequest {

    @NotBlank(message = "Job title is required")
    @Size(min = 2, max = 200, message = "Job title must be between 2 and 200 characters")
    private String jobTitle;

    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    private String companyName;

    @Pattern(regexp = "^https?://.*", message = "Job link must be a valid URL")
    private String jobLink;

    @NotNull(message = "Status is required")
    private Status status;

    @NotNull(message = "Source is required")
    private Source source;

    @NotNull(message = "Application mode is required")
    private ApplicationMode applicationMode;

    @NotNull(message = "Job type is required")
    private  JobType jobType;


    @Size(max = 1000, message = "Note cannot exceed 1000 characters")
    private String note;



}
