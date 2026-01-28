package shubham.JobTracker.Controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shubham.JobTracker.Dto.Request.UpdateUserRequest;
import shubham.JobTracker.Dto.Response.UserResponse;
import shubham.JobTracker.Entity.User;
import shubham.JobTracker.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/Admin")
@Slf4j
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;


    @GetMapping("/AllUser")
    public ResponseEntity<List<UserResponse>> allUser(){

        List<UserResponse> users  = userService.getAllUser();
        if(users != null && !users.isEmpty()){
            return  ResponseEntity.status(HttpStatus.OK).body(users);

        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/UserByUserName")
    public ResponseEntity<UserResponse> findUserByUserName(String name){

        User user= userService.getUserByUserName(name);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping("/DeleteAdmin")
    public ResponseEntity<Void> deleteAdmin(String name){

        userService.deleteByUserName(name);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/UpdateAdmin")
    public ResponseEntity<UserResponse> updateAdmin(@Valid @RequestBody UpdateUserRequest request){
       UserResponse response =  userService.updateUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


}
