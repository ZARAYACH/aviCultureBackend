package ma.ens.AviCultureBackend.medical.repository;

import ma.ens.AviCultureBackend.medical.modal.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepo extends JpaRepository<Disease, Long> {
}
