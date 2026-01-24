package shubham.JobTracker.Dto.Request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private  String name;

    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    private  String password;

    @Email(message = "Invalid email format")
    @Size(max = 255)
    private  String email;


}
