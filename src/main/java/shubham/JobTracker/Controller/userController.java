package shubham.JobTracker.Controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shubham.JobTracker.Dto.UserResponse;
import shubham.JobTracker.Entity.User;
import shubham.JobTracker.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/User")
public class userController {

    @Autowired
    private UserService userService;

    @GetMapping("/allUser")
   public ResponseEntity<UserResponse<List<User>>> allUser(){

       List<User> userList = userService.getAllUser();
       if(userList != null && ! userList.isEmpty()){
           return ResponseEntity.status(HttpStatus.OK).body(new UserResponse<>(userList,"All User"));
       }
       return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new UserResponse<>(null,"no User Found"));


   }
   @PostMapping ("/saveUser")
   public ResponseEntity<UserResponse<User>> saveUser(@RequestBody User user){

       userService.saveUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse<>(user,"userSaved Successfully"));

   }


}
