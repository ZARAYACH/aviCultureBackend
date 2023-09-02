package ma.ens.AviCultureBackend.user.repository;

import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.modal.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional
public interface UserSessionRepo extends JpaRepository<UserSession, String> {

	@Query(value = "FROM UserSession us WHERE us.id = :id AND us.user = :user")
	Optional<UserSession> findByIdAndUser(@Param("id") String id, @Param("user") User user);

	@Modifying
	@Query(value = "UPDATE UserSession us SET us.endDate = :endDate WHERE us.user = :user")
	void updateEndDateUserSessionsByUser(@Param("user") User user,@Param("endDate") LocalDateTime localDateTime);
}
