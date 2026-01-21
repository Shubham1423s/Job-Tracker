package shubham.JobTracker.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shubham.JobTracker.Dto.UserResponse;
import shubham.JobTracker.Entity.User;
import shubham.JobTracker.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/Admin")
@Slf4j
@PreAuthorize("hasRole('Admin')")
public class AdminController {

    @Autowired
    private UserService userService;


    @GetMapping("/AllUser")
    public ResponseEntity<UserResponse<List<User>>> allUser(){

        List<User> users  = userService.getAllUser();
        if(users != null && !users.isEmpty()){
            return  ResponseEntity.status(HttpStatus.OK).body(new UserResponse<>(users,"All Users"));

        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse<>(null,"No User Found"));
    }
    @GetMapping("/UserByUserName")
    public ResponseEntity<UserResponse<User>> findUserByUserName(String name){

        User user= userService.getUserByUserName(name);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(new UserResponse<>(user,"user "));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserResponse<>(null,"User Not Found"));

    }
    @GetMapping("/SaveAdmin")
    public ResponseEntity<UserResponse<User>>  saveAdmin(@RequestBody User user){

        userService.saveAdmin(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse<>(user,"User created successfully"));

    }
    @GetMapping("/DeleteAdmin")
    public ResponseEntity<UserResponse<User>> deleteAdmin(String name){

        userService.deleteByUserName(name);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(new UserResponse<>(null,"User Deleted Successfully"));
    }

    @GetMapping("/UpdateAdmin")
    public ResponseEntity<UserResponse<Void>> updateAdmin(@RequestBody User user){
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new UserResponse<>());

    }


}
