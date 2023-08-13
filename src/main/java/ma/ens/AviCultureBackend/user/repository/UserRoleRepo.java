package ma.ens.AviCultureBackend.user.repository;

import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

	@Query("SELECT ur FROM UserRole ur where ur.name =:name")
	Collection<UserRole> getUserRoleAuthByName(@Param("name") String name);

}
