package ma.ens.AviCultureBackend.transaction.repository;

import ma.ens.AviCultureBackend.transaction.model.Transaction;
import ma.ens.AviCultureBackend.transaction.model.TransactionProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionProductDetailRepo extends JpaRepository<TransactionProductDetail, String> {

    @Query("SELECT tpd FROM  TransactionProductDetail tpd WHERE tpd.transaction = :transaction")
    List<TransactionProductDetail> findAllByTransaction(Transaction transaction);
}
