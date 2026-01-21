package shubham.JobTracker.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {

    String userName;
    String newPassword;
}
