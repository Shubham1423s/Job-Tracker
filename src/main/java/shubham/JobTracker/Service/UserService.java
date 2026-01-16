package shubham.JobTracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shubham.JobTracker.Entity.User;
import shubham.JobTracker.Repository.UserRepo;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private static final  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepo userRepo;


   public  void saveUser(User user){
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setRoles(Arrays.asList("User"));
       userRepo.save(user);
   }
    public List<User> getAllUser(){

       return userRepo.findAll();
    }
    @Transactional
    public void deleteByUserName(String userName){

       userRepo.deleteByUserName(userName);
    }
    public void getUserByUserName(String userName){

        userRepo.findByUserName(userName);
    }


}
