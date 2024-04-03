package com.example.nienluan.services;

import org.springframework.security.access.prepost.PreAuthorize;


public interface RoleService {
  public Boolean isAdmin(int id);
}
