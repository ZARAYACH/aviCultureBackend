package ma.ens.AviCultureBackend.user.repository;

import ma.ens.AviCultureBackend.user.modal.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {

	@Query("SELECT ur FROM UserRole ur where ur.name =:name")
	Set<UserRole> getUserRoleByName(@Param("name") UserRole.Role name);

	@Query("SELECT ur FROM UserRole ur where ur.name in (:names)")
	Set<UserRole> getUserRolesByNameIn(@Param("names") List<UserRole.Role> names);

}
