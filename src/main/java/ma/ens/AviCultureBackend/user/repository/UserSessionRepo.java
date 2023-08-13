package ma.ens.AviCultureBackend.user.repository;

import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.modal.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSessionRepo extends JpaRepository<UserSession, String> {

	@Query(value = "FROM UserSession us WHERE us.id = :id AND us.user = :user")
	Optional<UserSession> findByIdAndUser(@Param("id") String id, @Param("user") User user);
}
