package tn.esprit.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.foyer.entities.Etudient;
import java.util.Optional;

@Repository
public interface EtudientRepository extends JpaRepository<Etudient, Long> {
    Optional<Etudient> findByCin(Long cin);
}