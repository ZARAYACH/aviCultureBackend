package ma.ens.AviCultureBackend.transaction.repository;

import ma.ens.AviCultureBackend.transaction.model.CounterParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterPartyRepo extends JpaRepository<CounterParty, Long> {

    Optional<CounterParty> findByEmailAddress(String emailAddress);
}
