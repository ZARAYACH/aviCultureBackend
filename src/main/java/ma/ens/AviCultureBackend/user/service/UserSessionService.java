package ma.ens.AviCultureBackend.user.service;

import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.modal.UserSession;
import ma.ens.AviCultureBackend.user.repository.UserSessionRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSessionService {

	private final UserSessionRepo userSessionRepo;
	public boolean isValideSession(String sessionId, User user) throws NotFoundException {
		UserSession session = userSessionRepo.findByIdAndUser(sessionId, user)
				.orElseThrow(NotFoundException::new);
		return session.isStillValid();
	}
}
