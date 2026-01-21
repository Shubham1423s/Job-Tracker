package shubham.JobTracker.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import shubham.JobTracker.Dto.AuthRequest;
import shubham.JobTracker.Dto.AuthResponse;
import shubham.JobTracker.Dto.ResetPasswordRequest;
import shubham.JobTracker.Dto.UserResponse;
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
    public ResponseEntity<UserResponse<User>> singUp(@RequestBody User user){
        if(userRepo.findByUserName(user.getUserName())!= null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse<>(null,"user Already Exist"));
        }
        userService.saveUser(user);;
        return ResponseEntity.status(HttpStatus.OK).body(new UserResponse<>(null,"User Registered Successfully"));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));

        String token = jwtUtil.generateToken(request.getUserName());
        return ResponseEntity.ok(new AuthResponse(token));

    }
    @PostMapping("/create-admin")
    public ResponseEntity<UserResponse<User>> newAdmin(@RequestBody  User user){
        if(userRepo.findByUserName(user.getUserName())!= null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse<>(user,"User Already Exist"));

        }
        userService.saveAdmin(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse<>(user,"Admin created"));

    }
    @PostMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestBody ResetPasswordRequest request){

        User user = userRepo.findByUserName(request.getUserName());
        if(user != null){
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepo.save(user);
            return ResponseEntity.ok("Password reset successfully");

        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse<>());

    }


}
