package com.example.nienluan.services.impls;

import com.example.nienluan.exceptions.AppException;
import com.example.nienluan.models.Role;
import com.example.nienluan.models.User;
import com.example.nienluan.repository.RoleRepository;
import com.example.nienluan.repository.UserRepository;
import com.example.nienluan.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

@Autowired
private RoleRepository roleRepository;
  @Autowired
  private UserRepository userRepository;
  @Override
  public Boolean isAdmin(int id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()){
      if(user.get().getRoles().get(0).getId() == 1){
        return true;
      }else {
        throw new AppException("Ban khong co quyen", HttpStatus.FORBIDDEN);
      }
    }
    throw new AppException("Tai khoan khong ton tai", HttpStatus.NOT_FOUND);
  }
}
