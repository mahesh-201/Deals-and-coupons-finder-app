package com.casestudy.profilemanagementmicroservice.resource;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.profilemanagementmicroservice.model.User;
import com.casestudy.profilemanagementmicroservice.repo.UserRepo;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public String joinGroup(@RequestBody User user) {
        user.setRoles(UserConstant.DEFAULT_ROLE);
        String encryptedPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPwd);
        repository.save(user);
        return "Hi " + user.getUserName() + " welcome to group !";
    }
   

    @GetMapping("/access/{userId}/{userRole}")
    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String giveAccessToUser(@PathVariable int userId, @PathVariable String userRole, Principal principal) {
        User user = repository.findById(userId).get();
        List<String> activeRoles = getRolesByLoggedInUser(principal);
        String newRole = "";
        if (activeRoles.contains(userRole)) {
            newRole = user.getRoles() + "," + userRole;
            user.setRoles(newRole);
        }
        repository.save(user);
        return "Hi " + user.getUserName() + " New Role assign to you by " + principal.getName();
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> loadUsers() {
        return repository.findAll();
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String testUserAccess() {
        return "user can only access this !";
    }
    
    @DeleteMapping("/delete/{id}")
    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteUserById(@PathVariable("id") int id)
    {
    	repository.deleteById(id);
    	System.out.println("id no :"+id+" is deleted");
    }

   
    
    
    
    private List<String> getRolesByLoggedInUser(Principal principal) {
        String roles = getLoggedInUser(principal).getRoles();
        List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
        if (assignRoles.contains("ROLE_ADMIN")) {
            return Arrays.stream(UserConstant.ADMIN_ACCESS).collect(Collectors.toList());
        }
       
        return Collections.emptyList();
    }

    
    
    
    private User getLoggedInUser(Principal principal) {
        return repository.findByUserName(principal.getName()).get();
    }
}