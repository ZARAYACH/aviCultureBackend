package ma.ens.AviCultureBackend.transaction.repository;

import ma.ens.AviCultureBackend.transaction.model.CounterParty;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CounterPartyRepo extends JpaRepository<CounterParty, Long> {

    Optional<CounterParty> findByEmailAddress(String emailAddress);

	@Query("SELECT c FROM CounterParty c WHERE c.type = :type")
	List<CounterParty> findAllByType(CounterParty.Type type);
}
