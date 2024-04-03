package com.example.nienluan.services;

import com.example.nienluan.dto.CredentialsDto;
import com.example.nienluan.dto.RegisterDto;
import com.example.nienluan.dto.UpdateUserRequest;
import com.example.nienluan.dto.UserDto;
import com.example.nienluan.dto.UserResponse;
import com.example.nienluan.exceptions.AppException;
import com.example.nienluan.mappers.UserMapper;
import com.example.nienluan.models.Role;
import com.example.nienluan.models.User;
import com.example.nienluan.repository.RoleRepository;
import com.example.nienluan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServices {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;

  public UserDto findByUsername(String username) {
    User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

    return userMapper.toUserDto(user);
  }

  public UserDto login(CredentialsDto credentialsDto) {

    User user = userRepository.findByUsername(credentialsDto.getUsername()).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

    if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())){
        return userMapper.toUserDto(user);
    }
    throw new AppException("Invalid password",HttpStatus.BAD_REQUEST);
  }

  public UserDto register(RegisterDto registerDto){
  Optional<User> userOptional = userRepository.findByUsername(registerDto.getUsername());
  if (userOptional.isPresent()){
    throw new AppException("User is existed", HttpStatus.BAD_REQUEST);
  }
    Optional<Role> userRole = roleRepository.findById(2);
    User user = userMapper.registerToUser(registerDto);
    user.setPassword(passwordEncoder.encode(CharBuffer.wrap(registerDto.getPassword())));
    user.setRoles(Arrays.asList(userRole.get()));
    User userSave = userRepository.save(user);

    return userMapper.toUserDto(userSave);
  }

  public UserDto getEmail(int id) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional == null && userOptional.isEmpty()){
      throw new AppException("User not exist", HttpStatus.BAD_REQUEST);
    }
    return userMapper.toUserDto(userOptional.get());

  }

  public UserResponse getUserInfor(int id) {
    Optional<User> user = userRepository.findById(id);
    if(user.isEmpty()){
      throw new AppException("Khong the tim thong tin nguoi dung", HttpStatus.NOT_FOUND);
    }
    UserResponse userResponse = UserResponse.builder()
            .name(user.get().getName())
            .age(user.get().getAge())
            .DOB(user.get().getDOB())
            .sex(user.get().getSex())
            .phoneNumber(user.get().getPhoneNumber())
            .address(user.get().getAddress())
            .build();
    return userResponse;
  }

  public void updateUser(int id, UpdateUserRequest updateUserRequest) {
    Optional<User> optionalUser = userRepository.findById(id);
    System.out.println(updateUserRequest);
    if(optionalUser.isEmpty()){
      throw new AppException("Khong the tim thong tin nguoi dung", HttpStatus.NOT_FOUND);
    }
    User user = optionalUser.get();
    if(!updateUserRequest.getName().equals(user.getName())){
      user.setName(updateUserRequest.getName());
    }
    if(!updateUserRequest.getAddress().equals(user.getAddress())){
      user.setAddress(updateUserRequest.getAddress());
    }
    if(updateUserRequest.getAge() != (user.getAge())){
      user.setAge(updateUserRequest.getAge());
    }
    if(updateUserRequest.getPhoneNumber() != (user.getPhoneNumber())){
      user.setPhoneNumber(updateUserRequest.getPhoneNumber());
    }
    userRepository.save(user);
    System.out.println("Cập nhật thông tin thành công");
  }
}
