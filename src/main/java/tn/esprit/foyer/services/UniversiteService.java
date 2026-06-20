package tn.esprit.foyer.services;

import tn.esprit.foyer.entities.Universite;

import java.util.List;

public interface UniversiteService {

    Universite saveUniversite (Universite universite);
    void deleteUniversite (Long idUniversite);
    Universite getUniversiteById (Long idUniversite);
    List<Universite> getAllUniversites();
}
