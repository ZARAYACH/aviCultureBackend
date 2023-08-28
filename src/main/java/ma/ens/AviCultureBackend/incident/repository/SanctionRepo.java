package ma.ens.AviCultureBackend.incident.repository;

import ma.ens.AviCultureBackend.incident.modal.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanctionRepo extends JpaRepository<Sanction, Long> {
}
