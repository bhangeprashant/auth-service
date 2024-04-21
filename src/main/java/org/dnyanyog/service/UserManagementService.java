package org.dnyanyog.service;

import java.util.List;
import java.util.Optional;
import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserManagementService {

  public Optional<AddUserResponse> addUser(AddUserRequest request);

  public AddUserResponse getSingleUser(String userId);

  public List<Users> getAllUser();

  public List<String> getAllUserIds();
}
