package tn.esprit.foyer.services;

import tn.esprit.foyer.entities.Etudient;

import java.util.List;

public interface EtudientService {
    Etudient saveEtudient (Etudient etudient);
    void deleteEtudient (Long idEtudient);
    Etudient getEtudientById (Long idEtudient);
    List<Etudient> getAllEtudients();
}
