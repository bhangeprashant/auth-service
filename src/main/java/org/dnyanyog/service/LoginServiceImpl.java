package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.common.ResponseCodes;
import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.encryption.EncryptionService;
import org.dnyanyog.entity.Users;
import org.dnyanyog.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UsersRepository userRepo;
	
	@Autowired
	EncryptionService encryptionService;

	public LoginResponse validateUser(LoginRequest loginRequest) {

		LoginResponse response = new LoginResponse();

		List<Users> liUser = null;
		try {
			liUser = userRepo.findByUsernameAndPassword(loginRequest.getUsername(),
					encryptionService.encrypt(loginRequest.getPassword()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (liUser.size() == 1) {
			response.setStatus(ResponseCodes.USER_LOGIN_SUCCESS.getCode());
			response.setMessage(ResponseCodes.USER_LOGIN_SUCCESS.getMessage());
		} else {
			response.setStatus(ResponseCodes.USER_LOGIN_FAIL.getCode());
			response.setMessage(ResponseCodes.USER_LOGIN_FAIL.getMessage());
		}

		return response;
	}
}
