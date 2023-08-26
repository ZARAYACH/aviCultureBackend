package ma.ens.AviCultureBackend.transaction.repository;

import ma.ens.AviCultureBackend.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, String> {
}
