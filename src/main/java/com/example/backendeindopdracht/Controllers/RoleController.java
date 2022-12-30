//package com.example.backendeindopdracht.Controllers;
//
//import com.example.backendeindopdracht.dtos.RoleDto;
//import com.example.backendeindopdracht.Models.Role;
//import com.example.backendeindopdracht.Repositories.RoleRepository;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RoleController {
//
//    private final RoleRepository repos;
//
//    public RoleController(RoleRepository repos) {
//        this.repos = repos;
//    }
//    @PostMapping("/roles")
//    public String createRole(@RequestBody RoleDto role) {
//        Role newRole = new Role();
//        newRole.setRoleName(role.getRoleName());
//        repos.save(newRole);
//
//        return "Done";
//    }
//}

