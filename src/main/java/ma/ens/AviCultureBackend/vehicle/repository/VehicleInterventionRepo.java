package ma.ens.AviCultureBackend.vehicle.repository;

import ma.ens.AviCultureBackend.vehicle.modal.Vehicle;
import ma.ens.AviCultureBackend.vehicle.modal.VehicleIntervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VehicleInterventionRepo extends JpaRepository<VehicleIntervention, Long> {

    @Query("SELECT vi FROM VehicleIntervention vi WHERE vi.vehicle = :vehicle")
    List<VehicleIntervention> findAllByVehicle(@Param("vehicle") Vehicle vehicle);

    @Query("SELECT vi FROM VehicleIntervention vi WHERE vi.id = :id AND vi.vehicle = :vehicle")
    Optional<VehicleIntervention> findByIdAndVehicle(@Param("id") Long id,@Param("vehicle") Vehicle vehicle);
}
