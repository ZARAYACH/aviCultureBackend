package ma.ens.AviCultureBackend.breeding.repository;

import ma.ens.AviCultureBackend.breeding.modal.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BuildingRepo extends JpaRepository<Building, Long> {

    @Query("SELECT b FROM Building b WHERE b.nature = 'STORAGE'")
    Optional<Building> findStorageBuildingById(Long id);

    @Query("SELECT b FROM Building b where b.nature = :buildingNature")
    List<Building> findAllByNature(Building.Nature buildingNature);
}
