package shubham.JobTracker.Dto.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {

    private String userName;
    private String newPassword;
}
