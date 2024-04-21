package org.dnyanyog.service;

import java.util.List;
import org.dnyanyog.dto.AuthorizationRequest;
import org.dnyanyog.dto.AuthrizationResponse;

public interface AuthorizationService {

  AuthrizationResponse authorize(AuthorizationRequest request);

  List<AuthrizationResponse> getAllAuthrs();
}
