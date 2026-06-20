package tn.esprit.foyer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.entities.Etudient;
import tn.esprit.foyer.entities.Reservation;
import tn.esprit.foyer.repositories.EtudientRepository;
import tn.esprit.foyer.repositories.ReservationRepository;

import java.util.List;

@Service

public class EtudientServiceImpl implements EtudientService {

    @Autowired
    private EtudientRepository etudientRepository;
    private ReservationRepository reservationRepository;

    @Override
    public Etudient saveEtudient(Etudient etudient) {
        return etudientRepository.save(etudient);
    }

    @Override
    public void deleteEtudient(Long idEtudient) {
        etudientRepository.deleteById(idEtudient);
    }

    @Override
    public Etudient getEtudientById(Long idEtudient) {
        return etudientRepository.findById(idEtudient).get();
    }

    @Override
    public List<Etudient> getAllEtudients() {
        return etudientRepository.findAll();
    }

}
