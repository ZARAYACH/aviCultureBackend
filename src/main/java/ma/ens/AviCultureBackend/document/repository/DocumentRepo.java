package ma.ens.AviCultureBackend.document.repository;

import ma.ens.AviCultureBackend.document.modal.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepo extends JpaRepository<Document, String> {
}
