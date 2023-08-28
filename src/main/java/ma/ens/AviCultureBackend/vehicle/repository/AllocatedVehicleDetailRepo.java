package ma.ens.AviCultureBackend.vehicle.repository;

import ma.ens.AviCultureBackend.breeding.modal.BreedingCenter;
import ma.ens.AviCultureBackend.vehicle.modal.AllocatedVehicleDetail;
import ma.ens.AviCultureBackend.vehicle.modal.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AllocatedVehicleDetailRepo extends JpaRepository<AllocatedVehicleDetail, Long> {
    @Query("SELECT a FROM AllocatedVehicleDetail a WHERE a.breedingCenter = :breedingCenter")
    List<AllocatedVehicleDetail> findAllByVehicle(@Param("breedingCenter") BreedingCenter breedingCenter);

    @Query("SELECT a FROM AllocatedVehicleDetail a WHERE a.vehicle = :vehicle")
    List<AllocatedVehicleDetail> findAllByVehicle(@Param("vehicle") Vehicle vehicle);

    @Query("SELECT a FROM AllocatedVehicleDetail a WHERE a.breedingCenter = :id AND a.breedingCenter = :breedingCenter")
    Optional<AllocatedVehicleDetail> findByIdAndBreedingCenter(@Param("id") Long id,@Param("breedingCenter") BreedingCenter center);
}
