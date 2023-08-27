package ma.ens.AviCultureBackend.vehicle.service;

import ma.ens.AviCultureBackend.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
}
