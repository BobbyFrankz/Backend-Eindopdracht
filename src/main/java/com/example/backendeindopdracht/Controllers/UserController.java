//package com.example.backendeindopdracht.Controllers;
//
//import com.example.backendeindopdracht.Repositories.RoleRepository;
//import com.example.backendeindopdracht.dtos.UserDto;
//import com.example.backendeindopdracht.Models.Role;
//import com.example.backendeindopdracht.Models.User;
//import com.example.backendeindopdracht.Repositories.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//public class UserController {
//
//    private final UserRepository userRepos;
//    private final RoleRepository roleRepos;
//    private final PasswordEncoder encoder;
//
//    public UserController(UserRepository userRepos, RoleRepository roleRepos, PasswordEncoder encoder) {
//        this.userRepos = userRepos;
//        this.roleRepos = roleRepos;
//        this.encoder = encoder;
//    }
//    @PostMapping("/users")
//    public String createUser(@RequestBody UserDto userDto) {
//        User newUser = new User();
//        newUser.setUsername(userDto.username);
//        newUser.setPassword(encoder.encode(userDto.password));
//
//        List<Role> userRoles = new ArrayList<>();
//        for (String rolename : userDto.roles) {
//            Optional<Role> or = roleRepos.findById(rolename);
//
//            userRoles.add(or.get());
//        }
//        newUser.setRoles(userRoles);
//
//        userRepos.save(newUser);
//
//        return "Done";
//    }
//}
