package com.example.nienluan.controllers;

import com.example.nienluan.dto.CreateRolesRequest;
import com.example.nienluan.models.Role;
import com.example.nienluan.repository.RoleRepository;
import com.example.nienluan.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/roles")
public class RoleController {
  private final RoleRepository roleRepository;

  @Autowired
  private RoleService roleService;
  public RoleController(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @GetMapping
  public String greeting(){
    return "Hi";
  }

  @PostMapping
  public ResponseEntity createRole(@RequestBody CreateRolesRequest createRolesRequest){
    Role role = new Role();
    role.setName(createRolesRequest.getName());
    roleRepository.save(role);
    return ResponseEntity.ok().body("Tao thanh cong");
  }

  @GetMapping("/{id}")
  public ResponseEntity<Boolean> isAdmin(@PathVariable int id){
    return ResponseEntity.accepted().body(roleService.isAdmin(id));
  }
}
