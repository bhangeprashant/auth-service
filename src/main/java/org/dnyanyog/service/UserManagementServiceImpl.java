package org.dnyanyog.service;

import static java.util.Objects.nonNull;
import java.util.List;
import java.util.Optional;
import org.dnyanyog.common.ResponseCodes;
import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.AddUserResponse;
import org.dnyanyog.encryption.EncryptionService;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.RolesRepository;
import org.dnyanyog.repo.UsersRepository;
import org.dnyanyog.utilities.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	Logger logger = LoggerFactory.getLogger(UserManagementServiceImpl.class);
	
	@Autowired
	UsersRepository userRepo; // Ask Spring to give object of 'Query class for Users' i.e UserRepository
	
	@Autowired
	AddUserResponse userResponse;

	@Autowired
	private List<String> userIds;

	@Autowired
	RolesRepository roleRepo;

	@Autowired
	EncryptionService encryptionService;

	public Optional<AddUserResponse> addUser(AddUserRequest request) {

		Users usersTable = new Users(); // Create table object in which we set data from request

		usersTable.setAge(request.getAge());
		usersTable.setEmail(request.getEmail());
		try {
			usersTable.setPassword(encryptionService.encrypt(request.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		usersTable.setUsername(request.getUsername());
		usersTable.setUserId("USR" + RandomStringGenerator.generateRandomString(5));

		/*
		 * 
		 * Users usersTable = Users.getInstance() .setAge(request.getAge())
		 * .setEmail(request.getEmail()) .setPassword(request.getPassword())
		 * .setUsername(request.getUsername())
		 * .setUserId("USR"+RandomStringGenerator.generateRandomString(5));
		 * 
		 */

		usersTable = userRepo.save(usersTable); // Ask repostiry to save the data from userTable to DB Table

		userResponse.setMessage(ResponseCodes.USER_ADD_SUCCESS.getMessage()); // Response set
		userResponse.setStatus(ResponseCodes.USER_ADD_SUCCESS.getCode());
		userResponse.setUserId(usersTable.getUserId());
		userResponse.getUserData().setEmail(usersTable.getEmail());
		userResponse.getUserData().setUsername(usersTable.getUsername());
		userResponse.getUserData().setPassword(usersTable.getPassword());
		userResponse.getUserData().setAge(usersTable.getAge());

		return Optional.of(userResponse);
	}

	public AddUserResponse getSingleUser(String userId) {

		Users user = userRepo.findByUserId(userId);

		if (null == user) {
			userResponse.setStatus(ResponseCodes.USER_NOT_FOUND.getCode());
			userResponse.setMessage(ResponseCodes.USER_NOT_FOUND.getMessage());
		} else {
			userResponse.setStatus(ResponseCodes.USER_FOUND_SUCCESS.getCode());
			userResponse.setMessage(ResponseCodes.USER_FOUND_SUCCESS.getMessage());
			userResponse.setUserId(user.getUserId());
			userResponse.getUserData().setEmail(user.getEmail());
			userResponse.getUserData().setUsername(user.getUsername());
			userResponse.getUserData().setPassword(user.getPassword());
			userResponse.getUserData().setAge(user.getAge());
		}
		return userResponse;
	}

	public List<Users> getAllUser() {
		return userRepo.findAll();
	}

	public List<String> getAllUserIds() {

		List<Users> users = userRepo.findAll();

		for (Users user : users) {
			if (nonNull(user)) {
				userIds.add(user.getUserId());
			}
		}
		return userIds;
	}
}
