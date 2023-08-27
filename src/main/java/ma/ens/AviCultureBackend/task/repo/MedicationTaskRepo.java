package ma.ens.AviCultureBackend.task.repo;

import ma.ens.AviCultureBackend.task.modal.MedicationTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationTaskRepo extends JpaRepository<MedicationTask, Long> {
}
