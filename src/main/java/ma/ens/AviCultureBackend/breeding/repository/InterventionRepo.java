package ma.ens.AviCultureBackend.breeding.repository;

import ma.ens.AviCultureBackend.breeding.modal.Building;
import ma.ens.AviCultureBackend.breeding.modal.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterventionRepo extends JpaRepository<Intervention, Long> {

    @Query("SELECT i FROM Intervention i WHERE i.building = :building")
    List<Intervention> findAllByBuilding(@Param("building") Building building);

    @Query("SELECT i FROM Intervention i WHERE i.id = :id AND i.building = :building")
    Optional<Intervention> findByIdAndBuilding(@Param("id") Long id, @Param("building") Building building);
}
