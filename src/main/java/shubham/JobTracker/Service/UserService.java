package shubham.JobTracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shubham.JobTracker.Dto.Request.CreateUserRequest;
import shubham.JobTracker.Dto.Request.UpdateUserRequest;
import shubham.JobTracker.Dto.Response.JobApplicationResponse;
import shubham.JobTracker.Dto.Response.UserResponse;
import shubham.JobTracker.Entity.JobApplication;
import shubham.JobTracker.Entity.User;
import shubham.JobTracker.Repository.UserRepo;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private static final  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepo userRepo;


   public UserResponse saveUser(CreateUserRequest request){

       User user = new User();

       user.setUserName(request.getUserName());
       user.setPassword(passwordEncoder.encode(request.getPassword()));
       user.setEmail(request.getEmail());
       user.setRole("USER");

       user.setCreatedAt(LocalDate.now());
       user.setLastLogin(LocalDate.now());
        User saved = userRepo.save(user);
       return mapToResponse(saved);

   }
    public  UserResponse saveAdmin(CreateUserRequest request){

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user  = userRepo.findByUserName(userName);

            user.setUserName(request.getUserName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
        user.setRole("ADMIN");
        user.setCreatedAt(LocalDate.now());
        user.setLastLogin(LocalDate.now());

       User saved =  userRepo.save(user);
       return mapToResponse(saved);
    }
    public List<UserResponse> getAllUser(){

       List<User> userList = userRepo.findAll();

       return userList.stream()
               .map(this::mapToResponse)
               .toList();


    }

    @Transactional
    public void deleteByUserName(String userName){
       userRepo.deleteByUserName(userName);
    }

    public User getUserByUserName(String userName){

       return userRepo.findByUserName(userName);
    }

    public UserResponse updateUser(UpdateUserRequest request){

       String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
       User existingUser = userRepo.findByUserName(currentUserName);


       if(request.getPassword() != null && !request.getPassword().isEmpty()){
           existingUser.setPassword(passwordEncoder.encode(request.getPassword()));
       }
       if(request.getName() != null && !request.getName().isEmpty()){
           existingUser.setUserName(request.getName());
       }
        if(request.getEmail() != null && !request.getEmail().isEmpty()){
            existingUser.setEmail(request.getEmail());
        }
       User saved =  userRepo.save(existingUser);
        return  mapToResponse(saved);


    }
    private UserResponse mapToResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId((long) user.getId());
        response.setUserName(user.getUserName());
        response.setPassword(user.getPassword());
        response.setEmail(user.getEmail());
        response.setLastLogin(user.getLastLogin());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }


}
