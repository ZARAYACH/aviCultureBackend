package ma.ens.AviCultureBackend.vehicle.repository;

import ma.ens.AviCultureBackend.vehicle.modal.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
}
