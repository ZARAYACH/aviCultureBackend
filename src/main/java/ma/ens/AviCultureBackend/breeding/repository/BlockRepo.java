package ma.ens.AviCultureBackend.breeding.repository;

import ma.ens.AviCultureBackend.breeding.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepo extends JpaRepository<Block, Long> {
}
