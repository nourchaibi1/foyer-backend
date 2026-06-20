package tn.esprit.foyer.services;

import tn.esprit.foyer.entities.Foyer;

import java.util.List;

public interface FoyerService {
    Foyer saveFoyer (Foyer foyer);
    void deleteFoyer (Long idFoyer);
    Foyer getFoyerById (Long idFoyer);
    List<Foyer> getAllFoyers();
}
