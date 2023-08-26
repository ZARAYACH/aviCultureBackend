package ma.ens.AviCultureBackend.breeding.repository;

import ma.ens.AviCultureBackend.breeding.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BuildingRepo extends JpaRepository<Building, Long> {

    @Query("SELECT b FROM Building b WHERE b.nature = 'STORAGE'")
    Optional<Building> findStorageBuildingById(Long id);
}
