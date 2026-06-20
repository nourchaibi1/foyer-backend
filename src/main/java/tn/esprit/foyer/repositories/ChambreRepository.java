package tn.esprit.foyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.foyer.entities.Chambre;
import tn.esprit.foyer.entities.TypeChambre;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    List<Chambre> findByTypeC(TypeChambre type);
    List<Chambre> findByBIdBloc (Long idBloc);
    List<Chambre> findByNumeroChambre (Long numeroChambre);
    List<Chambre> findAllByTypeC (TypeChambre typeChambre);
    Chambre findChambresByNumeroChambre(Long numeroChambre);
    List<Chambre> findByNumeroChambreAndTypeC(Long numeroChambre, TypeChambre typeChambre);
    List<Chambre> findByBNomBloc(String nomBloc);
    // Occupied = has at least one VALID reservation
    @Query("SELECT COUNT(DISTINCT c) FROM Chambre c JOIN c.r r WHERE r.estValide = true")
    long countChambresOccupees();

    // Free = no valid reservations
    @Query("SELECT COUNT(c) FROM Chambre c WHERE c.idChambre NOT IN " +
            "(SELECT DISTINCT r.chambre.idChambre FROM Reservation r WHERE r.estValide = true)")
    long countChambresLibres();
}
