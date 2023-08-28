package ma.ens.AviCultureBackend.user.repository;

import ma.ens.AviCultureBackend.user.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query("select u from User u where u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);
    @Query(value = "select u from User u where u.phoneNumber = :phoneNumber")
    Optional<User> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    @Query(value = "SELECT u FROM User u WHERE u.isDriver = true AND u.id in (:driverIds) ")
    List<User> findDriversByIds(@Param("driverIds") List<Long> driverIds);
}
