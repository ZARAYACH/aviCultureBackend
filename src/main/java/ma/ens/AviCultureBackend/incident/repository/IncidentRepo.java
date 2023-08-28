package ma.ens.AviCultureBackend.incident.repository;

import ma.ens.AviCultureBackend.incident.modal.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepo extends JpaRepository<Incident, Long> {
}
