package tn.esprit.foyer.services;

import tn.esprit.foyer.entities.Chambre;
import tn.esprit.foyer.entities.TypeChambre;

import java.util.List;

public interface ChamberService {
    Chambre saveChambre (Chambre chambre);
    void deleteChambre (Long idChambre);
    Chambre getChambreById (Long idChambre);
    List<Chambre> getAllChambres();

    List<Chambre> getChambreByType(TypeChambre type);

    List<Chambre> getChambreByBloc(Long idBloc);

    List<Chambre> getChambreByNumero(Long numero);

    List<Chambre> getByNumeroAndType(Long numero, TypeChambre type);

    List<Chambre> findBybnomBloc(String nomBloc);
    List<Chambre> findAllByTypeC (TypeChambre typeChambre);
    Chambre findChambresByNumeroChambre(Long numeroChambre);
}
