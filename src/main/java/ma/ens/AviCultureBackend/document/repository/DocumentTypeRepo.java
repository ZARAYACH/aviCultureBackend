package ma.ens.AviCultureBackend.document.repository;

import ma.ens.AviCultureBackend.document.modal.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRepo extends JpaRepository<DocumentType, Long> {
}
