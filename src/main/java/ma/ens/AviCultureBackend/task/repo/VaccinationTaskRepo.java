package ma.ens.AviCultureBackend.task.repo;

import ma.ens.AviCultureBackend.task.modal.VaccinationTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationTaskRepo extends JpaRepository<VaccinationTask, Long> {
}
