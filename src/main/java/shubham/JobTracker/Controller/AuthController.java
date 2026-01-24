package shubham.JobTracker.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import shubham.JobTracker.Config.JwtUtil;
import shubham.JobTracker.Config.JwtUtil;
import shubham.JobTracker.Dto.Request.CreateUserRequest;
import shubham.JobTracker.Dto.Request.LoginRequest;
import shubham.JobTracker.Dto.Response.AuthResponse;
import shubham.JobTracker.Dto.Request.ResetPasswordRequest;
import shubham.JobTracker.Dto.Response.UserResponse;
import shubham.JobTracker.Entity.User;
import shubham.JobTracker.Repository.UserRepo;
import shubham.JobTracker.Service.UserService;

@RestController
@RequestMapping("/Auth")
public class AuthController {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> singUp(@Valid  @RequestBody CreateUserRequest request){

      UserResponse response = userService.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));

        String token = jwtUtil.generateToken(request.getUserName());
        return ResponseEntity.ok(new AuthResponse(token));

    }
    @PostMapping("/create-admin")
    public ResponseEntity<UserResponse> newAdmin(@Valid @RequestBody  CreateUserRequest request){

       UserResponse response =  userService.saveAdmin(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @PostMapping("/forgotPassword")
    public ResponseEntity<UserResponse> forgotPassword( @Valid  @RequestBody ResetPasswordRequest request){

        User user = userRepo.findByUserName(request.getUserName());
        if(user != null){
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepo.save(user);
            return  ResponseEntity.status(HttpStatus.OK).build();

        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }


}
