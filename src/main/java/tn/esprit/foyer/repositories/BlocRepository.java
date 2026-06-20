package tn.esprit.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.foyer.entities.Bloc;

@Repository

public interface BlocRepository extends JpaRepository<Bloc,Long> {
}
